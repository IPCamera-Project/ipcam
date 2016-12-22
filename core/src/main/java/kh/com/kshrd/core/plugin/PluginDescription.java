package kh.com.kshrd.core.plugin;

import com.github.zafarkhaja.semver.Version;
import com.github.zafarkhaja.semver.expr.Expression;

import static com.github.zafarkhaja.semver.expr.CompositeExpression.Helper.gte;

/**
 * Created by sophatvathana on 20/12/16.
 */
public class PluginDescription {
    private String id;
    private String name;
    private String release;
    private String description;
    private String className;
    private Version version;
    private Expression requires;
    private String author;
    private String vendor;

    public PluginDescription(){
        requires = gte("0.0.0");
    }
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Version getVersion() {
        return version;
    }

    public void setVersion(Version version) {
        this.version = version;
    }

    public Expression getRequires() {
        return requires;
    }

    public void setRequires(Expression requires) {
        this.requires = requires;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getVendor() {
        return vendor;
    }

    public void setVendor(String vendor) {
        this.vendor = vendor;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRelease() {
        return release;
    }

    public void setRelease(String release) {
        this.release = release;
    }

    @Override
    public String toString() {
        return "PluginDescription{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", className='" + className + '\'' +
                ", version=" + version +
                ", requires=" + requires +
                ", author='" + author + '\'' +
                ", vendor='" + vendor + '\'' +
                '}';
    }
}
