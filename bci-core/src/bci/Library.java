package bci;

import bci.exceptions.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/** Class that represents the library as a whole. */
class Library implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    private int currentDate = 1;

    /* Domain collections */
    private Map<Integer, User> users = new HashMap<>();
    private Map<Integer, Work> works = new HashMap<>();
    private Map<String, Creator> creators = new HashMap<>();

    /*
    private List<Object> requests = new ArrayList<>();
    private List<Object> rules = new ArrayList<>();
    */

    /* File association */
    private String associatedFilename = null;

    public Library() {

    }


    /**
     * Save this library to the given filename using Java serialization.
     *
     * @param filename target file
     * @throws IOException on IO problems
     */
    public void savaToFile(String filename) throws IOException {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(filename))){
            out.writeObject(this);
            // store as associated file
            this.associatedFilename = filename;
        }
    }


    /**
     * Read the text input file at the beginning of the program and populates the
     * instances of the various possible types (books, DVDs, users).
     *
     * @param filename name of the file to load
     * @throws UnrecognizedEntryException
     * @throws IOException
     * FIXME maybe other exceptions
     */
    void importFile(String filename) throws UnrecognizedEntryException, IOException
	    /* FIXME maybe other exceptions */  {
      //FIXME implement method
    }

}
