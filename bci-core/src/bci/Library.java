package bci;

import bci.exceptions.*;
import java.io.IOException;
import java.io.Serializable;
import java.util.Map;
import java.util.HashMap;
import java.util.Collection;

/** Class that represents the library as a whole. */
class Library implements Serializable {

    @java.io.Serial
    private static final long serialVersionUID = 202507171003L;

    private int currentDate = 1;

    private Map<Integer, User> users = new HashMap<>();

    private Map<Integer, Work> works = new HashMap<>();

    private Map<String, Creator> creators = new HashMap<>();

    private int nextUserId = 1;
    private int nextWorkId = 1;
    //FIXME maybe define attributes
    //FIXME maybe implement constructor
    //FIXME maybe implement methods

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
