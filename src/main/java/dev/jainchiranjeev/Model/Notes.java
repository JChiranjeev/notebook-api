package dev.jainchiranjeev.Model;

import javax.persistence.*;

@Entity
@Table(name = "online_notebook_notes")
public class Notes {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "on_note_id")
    private int id;

    @Column(name = "on_username")
    private String username;

    @Column(name = "on_note_title")
    private String noteTitle;

    @Column(name = "on_note_content")
    private String noteContent;

    public Notes() {
    }

    public Notes(String username, String noteTitle, String noteContent) {
        this.username = username;
        this.noteTitle = noteTitle;
        this.noteContent = noteContent;
    }

    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getNoteTitle() {
        return this.noteTitle;
    }

    public void setNoteTitle(String noteTitle) {
        this.noteTitle = noteTitle;
    }

    public String getNoteContent() {
        return this.noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + id + "'" +
            ", username='" + getUsername() + "'" +
            ", noteTitle='" + getNoteTitle() + "'" +
            ", noteContent='" + getNoteContent() + "'" +
            "}";
    }

    public int getId() {
        return id;
    }
}