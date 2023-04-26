package com.agile.demo.biz.accountproject;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountProjectKey implements Serializable {
    private Long nap_seq;
    private String userId;
    private Long np_seq;

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof AccountProjectKey)) {
            return false;
        }
        AccountProjectKey other = (AccountProjectKey) obj;
        return Objects.equals(nap_seq, other.nap_seq) && Objects.equals(userId, other.userId) && Objects.equals(np_seq, other.np_seq);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nap_seq, userId, np_seq);
    }

}
