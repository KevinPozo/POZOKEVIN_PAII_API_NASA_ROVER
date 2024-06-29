/**
 *
 * @author KevinPozo
 * Title: Api Fotos de Rover Mars (Lambda).
 *
 *
 * */
package Model;

import java.time.LocalDate;
import java.util.List;

public class MarsPhoto {
    private int id;
    private int sol;
    private String cameraName;
    private String cameraFullName;
    private String imgSrc;
    private LocalDate earthDate;
    private String roverName;
    private String roverStatus;
    private LocalDate roverLandingDate;
    private LocalDate roverLaunchDate;
    private int roverMaxSol;
    private LocalDate roverMaxDate;
    private int roverTotalPhotos;
    private List<String> roverCameras;

    public MarsPhoto(int id, int sol, LocalDate earthDate, String roverName, String cameraName, String imgSrc) {
    this.id = id;
    this.sol = sol;
    this.earthDate = earthDate;
    this.roverName = roverName;
    this.cameraFullName = cameraName;
    this.imgSrc = imgSrc;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getSol() {
        return sol;
    }

    public void setSol(int sol) {
        this.sol = sol;
    }

    public String getCameraFullName() {
        return cameraFullName;
    }

    public void setCameraFullName(String cameraFullName) {
        this.cameraFullName = cameraFullName;
    }

    public String getCameraName() {
        return cameraName;
    }

    public void setCameraName(String cameraName) {
        this.cameraName = cameraName;
    }

    public String getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(String imgSrc) {
        this.imgSrc = imgSrc;
    }

    public LocalDate getEarthDate() {
        return earthDate;
    }

    public void setEarthDate(LocalDate earthDate) {
        this.earthDate = earthDate;
    }

    public String getRoverName() {
        return roverName;
    }

    public void setRoverName(String roverName) {
        this.roverName = roverName;
    }

    public String getRoverStatus() {
        return roverStatus;
    }

    public void setRoverStatus(String roverStatus) {
        this.roverStatus = roverStatus;
    }

    public LocalDate getRoverLaunchDate() {
        return roverLaunchDate;
    }

    public void setRoverLaunchDate(LocalDate roverLaunchDate) {
        this.roverLaunchDate = roverLaunchDate;
    }

    public LocalDate getRoverLandingDate() {
        return roverLandingDate;
    }

    public void setRoverLandingDate(LocalDate roverLandingDate) {
        this.roverLandingDate = roverLandingDate;
    }

    public int getRoverMaxSol() {
        return roverMaxSol;
    }

    public void setRoverMaxSol(int roverMaxSol) {
        this.roverMaxSol = roverMaxSol;
    }

    public LocalDate getRoverMaxDate() {
        return roverMaxDate;
    }

    public void setRoverMaxDate(LocalDate roverMaxDate) {
        this.roverMaxDate = roverMaxDate;
    }

    public int getRoverTotalPhotos() {
        return roverTotalPhotos;
    }

    public void setRoverTotalPhotos(int roverTotalPhotos) {
        this.roverTotalPhotos = roverTotalPhotos;
    }

    public List<String> getRoverCameras() {
        return roverCameras;
    }

    public void setRoverCameras(List<String> roverCameras) {
        this.roverCameras = roverCameras;
    }

    @Override
    public String toString() {
        return "MarsPhoto{" +
                "id=" + id +
                ", sol=" + sol +
                ", cameraName='" + cameraName + '\'' +
                ", cameraFullName='" + cameraFullName + '\'' +
                ", imgSrc='" + imgSrc + '\'' +
                ", earthDate=" + earthDate +
                ", roverName='" + roverName + '\'' +
                ", roverStatus='" + roverStatus + '\'' +
                ", roverLandingDate=" + roverLandingDate +
                ", roverLaunchDate=" + roverLaunchDate +
                ", roverMaxSol=" + roverMaxSol +
                ", roverMaxDate=" + roverMaxDate +
                ", roverTotalPhotos=" + roverTotalPhotos +
                ", roverCameras=" + roverCameras +
                '}';
    }
}
