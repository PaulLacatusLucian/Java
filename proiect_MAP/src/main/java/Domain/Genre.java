package Domain;

import java.util.UUID;

public class Genre {
    private final UUID genreId;
    private String name;
    private String description;


    public Genre(String name, String description) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }

        if (description == null || description.isEmpty()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }

        genreId = UUID.randomUUID();
        this.name = name;
        this.description = description;
    }


    public UUID getId() {
        return this.genreId;
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return this.description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Domain.Genre{" +
                "genreId=" + genreId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}
