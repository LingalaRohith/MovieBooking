package com.example.movie.resources.model;

import com.example.movie.resources.enums.MovieAvailability;
import jakarta.persistence.*;

import java.sql.Date;

@Entity
@Table(name = "movie")
public class Movie {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private int id;
    private String movieTitle;
    @Column(length = 1000)
    private String movieCast;
    private String movieCategory;
    private String movieDirector;
    private String movieProducer;
    private Date releaseDate;
    @Column(length = 1500)
    private String synopsis;
    @Column(length = 1500)
    private String reviews;
    private String trailerLink;
    private String movieCertificationCode;
    private int rating;
    @Enumerated(EnumType.ORDINAL)
    private MovieAvailability movieAvailability;
    private String posterSrc;
    private String bannerSrc;
    private String language;



    public Movie() {
        super();
    }

    public Movie(int id, String movieTitle, String movieCast, String movieCategory, String movieDirector, String movieProducer, Date releaseDate, String synopsis, String reviews, String trailerLink, String movieCertificationCode, int rating,/* MovieAvailability movieAvailability, */String posterSrc, String bannerSrc/*, String language*/) {
        this.id = id;
        this.movieTitle = movieTitle;
        this.movieCast = movieCast;
        this.movieCategory = movieCategory;
        this.movieDirector = movieDirector;
        this.movieProducer = movieProducer;
        this.releaseDate = releaseDate;
        this.synopsis = synopsis;
        this.reviews = reviews;
        this.trailerLink = trailerLink;
        this.movieCertificationCode = movieCertificationCode;
        this.rating = rating;
        this.movieAvailability = movieAvailability;
        this.posterSrc = posterSrc;
        this.bannerSrc = bannerSrc;
        this.language = language;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public String getMovieCast() {
        return movieCast;
    }

    public void setMovieCast(String movieCast) {
        this.movieCast = movieCast;
    }

    public String getMovieCategory() {
        return movieCategory;
    }

    public void setMovieCategory(String movieCategory) {
        this.movieCategory = movieCategory;
    }

    public String getMovieDirector() {
        return movieDirector;
    }

    public void setMovieDirector(String movieDirector) {
        this.movieDirector = movieDirector;
    }

    public String getMovieProducer() {
        return movieProducer;
    }

    public void setMovieProducer(String movieProducer) {
        this.movieProducer = movieProducer;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public String getReviews() {
        return reviews;
    }

    public void setReviews(String reviews) {
        this.reviews = reviews;
    }

    public String getTrailerLink() {
        return trailerLink;
    }

    public void setTrailerLink(String trailerLink) {
        this.trailerLink = trailerLink;
    }

    public String getMovieCertificationCode() {
        return movieCertificationCode;
    }

    public void setMovieCertificationCode(String movieCertificationCode) {
        this.movieCertificationCode = movieCertificationCode;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public MovieAvailability getMovieAvailability() {
        return movieAvailability;
    }

    public void setMovieAvailability(MovieAvailability movieAvailability) {
        this.movieAvailability = movieAvailability;
    }

    public String getPosterSrc() {
        return posterSrc;
    }

    public void setPosterSrc(String posterSrc) {
        this.posterSrc = posterSrc;
    }

    public String getBannerSrc() {
        return bannerSrc;
    }

    public void setBannerSrc(String bannerSrc) {
        this.bannerSrc = bannerSrc;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }
}
