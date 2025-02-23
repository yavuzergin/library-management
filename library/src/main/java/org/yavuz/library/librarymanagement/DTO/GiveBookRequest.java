package org.yavuz.library.librarymanagement.DTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class GiveBookRequest {
    private long memberId;
    private long bookId;
        private Date issueDate;
    private int borrowDuration;
}
