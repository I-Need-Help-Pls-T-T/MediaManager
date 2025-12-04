package com.univer.mediamanager.model.foruser;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class UserMediaStatusId implements Serializable {
    private Long user;
    private Long mediaItem;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserMediaStatusId that = (UserMediaStatusId) o;
        return Objects.equals(user, that.user) &&
                Objects.equals(mediaItem, that.mediaItem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, mediaItem);
    }
}