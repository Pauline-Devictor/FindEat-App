package etu.ihm.myactivity;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class PlacesApiParser {

    @JsonProperty("results")
    private List<ResultsDTO> results;
    @JsonProperty("status")
    private String status;

    public List<ResultsDTO> getResults() {
        return results;
    }

    public void setResults(List<ResultsDTO> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    //Mettre meme nom de variable que dans json pour bon parsage
    public static class ResultsDTO {
        @JsonProperty("business_status")
        private String business_status;
        @JsonProperty("formatted_address")
        private String formatted_address;
        @JsonProperty("geometry")
        private GeometryDTO geometry;
        @JsonProperty("icon")
        private String icon;
        @JsonProperty("icon_background_color")
        private String icon_background_color;
        @JsonProperty("icon_mask_base_uri")
        private String icon_mask_base_uri;
        @JsonProperty("name")
        private String name;
        @JsonProperty("opening_hours")
        private OpeningHoursDTO opening_hours;
        @JsonProperty("photos")
        private List<PhotosDTO> photos;
        @JsonProperty("place_id")
        private String place_id;
        @JsonProperty("price_level")
        private Integer price_level;
        @JsonProperty("rating")
        private Double rating;
        @JsonProperty("types")
        private List<String> types;
        @JsonProperty("user_ratings_total")
        private Integer user_ratings_total;
        @JsonProperty("vicinity")
        private String vicinity;

        public String getBusiness_status() {
            return business_status;
        }

        public void setBusiness_status(String business_status) {
            this.business_status = business_status;
        }

        public String getFormatted_address() {
            return formatted_address;
        }

        public void setFormatted_address(String formatted_address) {
            this.formatted_address = formatted_address;
        }

        public GeometryDTO getGeometry() {
            return geometry;
        }

        public void setGeometry(GeometryDTO geometry) {
            this.geometry = geometry;
        }

        public String getIcon() {
            return icon;
        }

        public void setIcon(String icon) {
            this.icon = icon;
        }

        public String getIcon_background_color() {
            return icon_background_color;
        }

        public void setIcon_background_color(String icon_background_color) {
            this.icon_background_color = icon_background_color;
        }

        public String getIcon_mask_base_uri() {
            return icon_mask_base_uri;
        }

        public void setIcon_mask_base_uri(String icon_mask_base_uri) {
            this.icon_mask_base_uri = icon_mask_base_uri;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public OpeningHoursDTO getOpening_hours() {
            return opening_hours;
        }

        public void setOpening_hours(OpeningHoursDTO opening_hours) {
            this.opening_hours = opening_hours;
        }

        public List<PhotosDTO> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosDTO> photos) {
            this.photos = photos;
        }

        public String getPlace_id() {
            return place_id;
        }

        public void setPlace_id(String place_id) {
            this.place_id = place_id;
        }

        public Integer getPrice_level() {
            return price_level;
        }

        public void setPrice_level(Integer price_level) {
            this.price_level = price_level;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public List<String> getTypes() {
            return types;
        }

        public void setTypes(List<String> types) {
            this.types = types;
        }

        public Integer getUser_ratings_total() {
            return user_ratings_total;
        }

        public void setUser_ratings_total(Integer user_ratings_total) {
            this.user_ratings_total = user_ratings_total;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public static class GeometryDTO {
            @JsonProperty("location")
            private LocationDTO location;

            public LocationDTO getLocation() {
                return location;
            }

            public void setLocation(LocationDTO location) {
                this.location = location;
            }

            public static class LocationDTO {
                @JsonProperty("lat")
                private Double lat;
                @JsonProperty("lng")
                private Double lng;

                public Double getLat() {
                    return lat;
                }

                public void setLat(Double lat) {
                    this.lat = lat;
                }

                public Double getLng() {
                    return lng;
                }

                public void setLng(Double lng) {
                    this.lng = lng;
                }
            }
        }

        public static class OpeningHoursDTO {
            @JsonProperty("open_now")
            private Boolean open_now;

            public Boolean getOpen_now() {
                return open_now;
            }

            public void setOpen_now(Boolean open_now) {
                this.open_now = open_now;
            }
        }

        public static class PhotosDTO {
            @JsonProperty("height")
            private Integer height;
            @JsonProperty("html_attributions")
            private List<String> htmlAttributions;
            @JsonProperty("photo_reference")
            private String photo_reference;
            @JsonProperty("width")
            private Integer width;

            public Integer getHeight() {
                return height;
            }

            public void setHeight(Integer height) {
                this.height = height;
            }

            public List<String> getHtmlAttributions() {
                return htmlAttributions;
            }

            public void setHtmlAttributions(List<String> htmlAttributions) {
                this.htmlAttributions = htmlAttributions;
            }

            public String getPhoto_reference() {
                return photo_reference;
            }

            public void setPhoto_reference(String photo_reference) {
                this.photo_reference = photo_reference;
            }

            public Integer getWidth() {
                return width;
            }

            public void setWidth(Integer width) {
                this.width = width;
            }
        }
    }
}