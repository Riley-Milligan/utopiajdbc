package tables;

import java.util.Objects;

public class PlaneType {

    private Integer id;
    private Integer maxCapacity;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getMaxCapacity() {
        return maxCapacity;
    }

    public void setMaxCapacity(Integer maxCapacity) {
        this.maxCapacity = maxCapacity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PlaneType planeType = (PlaneType) o;
        return Objects.equals(id, planeType.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
