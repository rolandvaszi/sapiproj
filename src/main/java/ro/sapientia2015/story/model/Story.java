package ro.sapientia2015.story.model;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * @author Kiss Tibor
 */
@Entity
@Table(name="story")
public class Story {

    public static final int MAX_LENGTH_DESCRIPTION = 500;
    public static final int MAX_LENGTH_TITLE = 100;
    public static final int MAX_LENGTH_COMMENT = 250;
    public static final int MAX_LENGTH_TEAM = 20;
    public static final int MAX_LENGTH_POINTS = 1;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "creation_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime creationTime;

    @Column(name = "description", nullable = true, length = MAX_LENGTH_DESCRIPTION)
    private String description;

    @Column(name = "modification_time", nullable = false)
    @Type(type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    private DateTime modificationTime;

    @Column(name = "title", nullable = false, length = MAX_LENGTH_TITLE)
    private String title;
    
    //@Column(name = 'comment', nullable = false, length = MAX_LENGTH_COMMENT)
    private String comment;
    
    private String team;
    
    @NotNull
    private int points;

    @Version
    private long version;

    public Story() {

    }

    public static Builder getBuilder(String title) {
        return new Builder(title);
    }

    public Long getId() {
        return id;
    }

    public DateTime getCreationTime() {
        return creationTime;
    }

    public String getDescription() {
        return description;
    }
    
    public String getComment() {
    	return comment;
    }
    
    public String getTeam() {
    	return team;
    }

    public DateTime getModificationTime() {
        return modificationTime;
    }

    public String getTitle() {
        return title;
    }
    
    public int getPoints() {
    	return points;
    }

    public long getVersion() {
        return version;
    }

    @PrePersist
    public void prePersist() {
        DateTime now = DateTime.now();
        creationTime = now;
        modificationTime = now;
    }

    @PreUpdate
    public void preUpdate() {
        modificationTime = DateTime.now();
    }

    public void update(String description, String title, String comment, String team, int points) {
        this.description = description;
        this.title = title;
        this.comment = comment;
        this.team = team;
        this.points = points;
    }

    public static class Builder {

        private Story built;

        public Builder(String title) {
            built = new Story();
            built.title = title;
        }

        public Story build() {
            return built;
        }

        public Builder description(String description) {
            built.description = description;
            return this;
        }
        
        public Builder comment(String comment) {
            built.comment = comment;
            return this;
        }
        
        public Builder team(String team) {
            built.team = team;
            return this;
        }
        
        public Builder points(int points) {
            built.points = points;
            return this;
        }
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this);
    }
}
