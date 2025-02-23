package org.yavuz.library.librarymanagement.DTO;

import lombok.Getter;
import lombok.Setter;
import org.yavuz.library.librarymanagement.model.LibraryManagement;

@Getter
@Setter
public class RetrieveMemberBooksResponse {
    private String memberName;
    private String bookName;

    public RetrieveMemberBooksResponse (LibraryManagement libraryManagement) {
        this.setMemberName(libraryManagement.getMember().getMemberFirstName());
        this.setBookName(libraryManagement.getBook().getBookName());
    }
}
