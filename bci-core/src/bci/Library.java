package bci;

import bci.exceptions.*;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.*;

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
     * Load a Library instance from the serialized file.
     *
     * @param filename source file
     * @return Library object read from file
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public static Library loadFromFile(String filename) throws IOException, ClassNotFoundException {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(filename))){
            Object obj = in.readObject();
            if (!(obj instanceof Library))
                throw new IOException("File does not contain a Library object");
            Library lib = (Library) obj;
            lib.associatedFilename = filename;
            return lib;
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
    void importFile(String filename) throws UnrecognizedEntryException, IOException {
        Objects.requireNonNull(filename, "filename");
        try (BufferedReader r = new BufferedReader(new InputStreamReader(new FileInputStream(filename), StandardCharsets.UTF_8))) {
            String line;
            int lineno = 0;
            while ((line = r.readLine()) != null) {
                lineno++;
                line = line.trim();
                if (line.isEmpty()) continue;
                String[] parts = line.split(":", -1);
                String type = parts[0].trim();
                try {
                    if ("USER".equalsIgnoreCase(type)) {
                        // USER:name:email
                        if (parts.length != 3) throw new IllegalArgumentException("USER wrong number of fields");
                        String name = parts[1].trim();
                        String email = parts[2].trim();
                        if (name.isEmpty() || email.isEmpty())
                            throw new IllegalArgumentException("USER empty name/email");
                        registerUser(name, email);
                    } else if ("BOOK".equalsIgnoreCase(type)) {
                        // BOOK:title:authors:price:category:ISBN:copies
                        if (parts.length != 7) throw new IllegalArgumentException("BOOK wrong number of fields");
                        String title = parts[1].trim();
                        String authorsString = parts[2].trim();
                        String priceString = parts[3].trim();
                        String categoryString = parts[4].trim();
                        String isbn = parts[5].trim();
                        String copiesString = parts[6].trim();

                        int price = Integer.parseInt(priceString);
                        int copies = Integer.parseInt(copiesString);
                        Category category = new Category(categoryString) {
                        };

                        List<String> authors = parseAuthors(authorsString);
                        // create or reuse Creator objects for each author
                        List<Creator> authorObjs = new ArrayList<>();
                        for (String an : authors) {
                            Creator c = creators.computeIfAbsent(an, Creator::new);
                            authorObjs.add(c);
                        }
                        Book book = new Book(allocateWorkId(), title, price, copies, category, isbn, authorObjs);
                        addWorkInternal(book);
                    } else if ("DVD".equalsIgnoreCase(type)) {
                        // DVD:title:director:price:category:IGAC:copies
                        if (parts.length != 7) throw new IllegalArgumentException("DVD wrong number of fields");
                        String title = parts[1].trim();
                        String directorName = parts[2].trim();
                        String priceString = parts[3].trim();
                        String categoryString = parts[4].trim();
                        String igac = parts[5].trim();
                        String copiesString = parts[6].trim();

                        int price = Integer.parseInt(priceString);
                        int copies = Integer.parseInt(copiesString);
                        Category category = new Category(categoryString) {
                        };
                        Creator director = creators.computeIfAbsent(directorName, Creator::new);
                        DVD dvd = new DVD(allocateWorkId(), title, price, copies, category, igac, director);
                        addWorkInternal(dvd);
                    } else {
                        throw new UnrecognizedEntryException("Unknown entry type '" + type + "' at line " + lineno);
                    }
                } catch (IllegalArgumentException | IndexOutOfBoundsException ex) {
                    throw new UnrecognizedEntryException("Malformed entry at line " + lineno + ": " + ex.getMessage(), ex);
                }
            }
        }
    }

    private static List<String> parseAuthors(String authorsString) {
        String[] toks = authorsString.split(",");
        List<String> result = new ArrayList<>();
        for (String t : toks) {
            String name = t.trim();
            if (!name.isEmpty()) result.add(name);
        }
        return result;
    }


    public User registerUser(String name, String email) {
        if (name == null || name.trim().isEmpty()) throw new IllegalArgumentException("name cannot be empty");
        if (email == null || email.trim().isEmpty()) throw new IllegalArgumentException("email cannot be empty");
        int id = allocateUserId();
        User u = new User(id, name.trim(), email.trim());
        users.put(u.getId(), u);
        return u;
    }

    private int allocateUserId() {
        return nextUserId++;
    }
    private int nextUserId = 1;

    private int allocateWorkId() {
        return nextWorkId++;
    }
    private int nextWorkId = 1;

    private void addWorkInternal(Work work) {
        works.put(work.getId(), work);
        // update creator(s)
        for(Creator c : work.getCreators()) {
            c.addWork(work);
            creators.putIfAbsent(c.getName(), c);
        }
    }
}
