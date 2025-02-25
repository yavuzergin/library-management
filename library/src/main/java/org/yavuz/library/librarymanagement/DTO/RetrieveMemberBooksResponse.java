package org.yavuz.library.librarymanagement.DTO;

import lombok.Getter;
import lombok.Setter;
import org.yavuz.library.librarymanagement.model.LibraryManagement;

@Getter
@Setter
public class RetrieveMemberBooksResponse {
    private String memberName;
    private String memberLastName;
    private String bookName;
    private String bookAuthor;

    public RetrieveMemberBooksResponse(LibraryManagement libraryManagement) {
        this.setMemberName(libraryManagement.getMember().getMemberFirstName());
        this.setMemberLastName(libraryManagement.getMember().getMemberLastName());
        this.setBookName(libraryManagement.getBook().getBookName());
        this.setBookAuthor(libraryManagement.getBook().getBookAuthor());
    }
}
