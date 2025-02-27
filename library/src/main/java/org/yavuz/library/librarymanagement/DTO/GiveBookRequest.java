package org.yavuz.library.librarymanagement.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class GiveBookRequest {
    private long memberId;
    private long bookId;
    private LocalDate issueDate;
    private int borrowDuration;
}
