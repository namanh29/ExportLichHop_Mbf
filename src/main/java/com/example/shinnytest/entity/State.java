package com.example.shinnytest.entity;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class State {
    private boolean opened;
    private boolean disabled;
    private boolean selected;

}
