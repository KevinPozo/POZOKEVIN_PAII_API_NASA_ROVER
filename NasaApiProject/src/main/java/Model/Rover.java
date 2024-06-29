/**
 *
 * @author KevinPozo
 * Title: Api Fotos de Rover Mars (Lambda).
 *
 *
 * */
package Model;

import java.time.LocalDate;

public class Rover {
    private int id;
    private String name;
    private LocalDate landingDate;
    private LocalDate launchDate;
    private ERoverType type;
    private int maxSol;
    private LocalDate maxDate;
    private int totalPhotos;

    public Rover(int id, String name, LocalDate landingDate, LocalDate launchDate, ERoverType type, int maxSol, LocalDate maxDate, int totalPhotos) {
        this.id = id;
        this.name = name;
        this.landingDate = landingDate;
        this.launchDate = launchDate;
        this.type = type;
        this.maxSol = maxSol;
        this.maxDate = maxDate;
        this.totalPhotos = totalPhotos;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getLandingDate() {
        return landingDate;
    }

    public void setLandingDate(LocalDate landingDate) {
        this.landingDate = landingDate;
    }

    public LocalDate getLaunchDate() {
        return launchDate;
    }

    public void setLaunchDate(LocalDate launchDate) {
        this.launchDate = launchDate;
    }

    public ERoverType getType() {
        return type;
    }

    public void setType(ERoverType type) {
        this.type = type;
    }

    public int getMaxSol() {
        return maxSol;
    }

    public void setMaxSol(int maxSol) {
        this.maxSol = maxSol;
    }

    public LocalDate getMaxDate() {
        return maxDate;
    }

    public void setMaxDate(LocalDate maxDate) {
        this.maxDate = maxDate;
    }

    public int getTotalPhotos() {
        return totalPhotos;
    }

    public void setTotalPhotos(int totalPhotos) {
        this.totalPhotos = totalPhotos;
    }

    @Override
    public String toString() {
        return "Rover{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", landingDate=" + landingDate +
                ", launchDate=" + launchDate +
                ", type=" + type +
                ", maxSol=" + maxSol +
                ", maxDate=" + maxDate +
                ", totalPhotos=" + totalPhotos +
                '}';
    }
}
