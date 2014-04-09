/*
 * 
 */
package cl.bluex.entidades;

import java.util.List;

// TODO: Auto-generated Javadoc
/**
 * The Class DirectoryFiles.
 */
public class DirectoryFiles {

    /** The files in directory. */
    private List<String> filesInDirectory;

    /** The path. */
    private String path;

    /**
     * Gets the files in directory.
     * @return the filesInDirectory
     */
    public final List<String> getFilesInDirectory() {
        return this.filesInDirectory;
    }

    /**
     * Gets the path.
     * @return the path
     */
    public final String getPath() {
        return this.path;
    }

    /**
     * Sets the files in directory.
     * @param filesInDirectory the filesInDirectory to set
     */
    public final void setFilesInDirectory(final List<String> filesInDirectory) {
        this.filesInDirectory = filesInDirectory;
    }

    /**
     * Sets the path.
     * @param path the path to set
     */
    public final void setPath(final String path) {
        this.path = path;
    }

}
