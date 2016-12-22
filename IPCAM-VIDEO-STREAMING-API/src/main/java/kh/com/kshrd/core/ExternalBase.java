package kh.com.kshrd.core;

/**
 * Created by sophatvathana on 15/12/16.
 */
import java.awt.*;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.DigestInputStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExternalBase {
    static private Logger logger = LoggerFactory.getLogger(ExternalBase.class);

    protected static String getOSType() {
        String name = System.getProperty("os.name").toLowerCase();
        System.out.print(System.getProperty("os.name"));
        if (name.contains("Windows"))
            return "windows";
        else if (name.contains("mac"))
            return "mac";
        else
            return "linux";
    }
    /**
     * Extract the specified library file to the target folder
     *
     * @param basePath
     * @param libraryFileName
     * @param targetFolder
     * @return
     */
    protected static boolean extractAndLoadLibraryFile(String basePath, String libraryFileName, String targetFolder) {
        //logger.debug("Load Library: {}{}", basePath, libraryFileName);
        System.out.println(String.format("Load Library: %s %s", basePath, libraryFileName));
        String nativeLibraryFilePath = basePath + libraryFileName;

        String extractedLibFileName = libraryFileName;
        File extractedLibFile = new File(targetFolder, extractedLibFileName);

        try {
            if (extractedLibFile.exists()) {
                // test md5sum value
                String md5sum1 = md5sum(ExternalBase.class.getResourceAsStream(nativeLibraryFilePath));
                String md5sum2 = md5sum(new FileInputStream(extractedLibFile));

                if (md5sum1.equals(md5sum2)) {
                    return loadNativeLibrary(targetFolder, extractedLibFileName);
                }
                else {
                    // remove old native library file
                    boolean deletionSucceeded = extractedLibFile.delete();
                    if (!deletionSucceeded) {
                        throw new IOException("failed to remove existing native library file: "
                                + extractedLibFile.getAbsolutePath());
                    }
                }
            }

            // extract file into the current directory
            //logger.debug("Before Loading:{}", nativeLibraryFilePath);
            System.out.println(String.format("Before Loading: %s", nativeLibraryFilePath));
            InputStream reader = ExternalBase.class.getResourceAsStream(nativeLibraryFilePath);
            //logger.debug("make iputStream:{}", reader);
            FileOutputStream writer = new FileOutputStream(extractedLibFile);
            byte[] buffer = new byte[1024];
            int bytesRead = 0;
            while ((bytesRead = reader.read(buffer)) != -1) {
                writer.write(buffer, 0, bytesRead);
            }

            writer.close();
            reader.close();

            if (!System.getProperty("os.name").contains("Windows")) {
                try {
                    Runtime.getRuntime().exec(new String[] { "chmod", "755", extractedLibFile.getAbsolutePath() })
                            .waitFor();
                }
                catch (Throwable e) {}
            }

            return loadNativeLibrary(targetFolder, extractedLibFileName);
        }
        catch (IOException e) {
            System.err.println(e.getMessage());
            return false;
        }

    }


    /**
     * Computes the MD5 value of the input stream
     *
     * @param input
     * @return
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    protected static String md5sum(InputStream input) throws IOException {
        BufferedInputStream in = new BufferedInputStream(input);

        try {
            MessageDigest digest = java.security.MessageDigest.getInstance("MD5");
            DigestInputStream digestInputStream = new DigestInputStream(in, digest);
            for (; digestInputStream.read() >= 0;) {

            }
            ByteArrayOutputStream md5out = new ByteArrayOutputStream();
            md5out.write(digest.digest());
            return md5out.toString();
        }
        catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("MD5 algorithm is not available: " + e);
        }
        finally {
            in.close();
        }
    }

    protected static synchronized boolean loadNativeLibrary(String path, String name) {
        File libPath = new File(path, name);
        if (libPath.exists()) {
            logger.info("LOAD: {}", libPath.getAbsolutePath());

            try {
                System.out.println(new File(path, name).getAbsolutePath());
                System.load(new File(path, name).getAbsolutePath());
                return true;
            }
            catch (UnsatisfiedLinkError e) {
                logger.error(e.getMessage());
                System.err.println(e);
                return false;
            }

        }
        else
            return false;
    }
    private static void main(String[] arg){
        ExternalBase.getOSType();
    }
}
