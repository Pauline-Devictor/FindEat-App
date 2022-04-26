package etu.ihm.myactivity;

import java.util.List;

public class PlacesApiParser {



    @com.fasterxml.jackson.annotation.JsonProperty("results")
    private List<ResultsDTO> results;
    @com.fasterxml.jackson.annotation.JsonProperty("status")
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

    public static class ResultsDTO {
        @com.fasterxml.jackson.annotation.JsonProperty("business_status")
        private String businessStatus;
        @com.fasterxml.jackson.annotation.JsonProperty("geometry")
        private ResultsDTO.GeometryDTO geometry;
        @com.fasterxml.jackson.annotation.JsonProperty("icon")
        private String icon;
        @com.fasterxml.jackson.annotation.JsonProperty("icon_background_color")
        private String iconBackgroundColor;
        @com.fasterxml.jackson.annotation.JsonProperty("icon_mask_base_uri")
        private String iconMaskBaseUri;
        @com.fasterxml.jackson.annotation.JsonProperty("name")
        private String name;
        @com.fasterxml.jackson.annotation.JsonProperty("opening_hours")
        private ResultsDTO.OpeningHoursDTO openingHours;
        @com.fasterxml.jackson.annotation.JsonProperty("photos")
        private List<PhotosDTO> photos;
        @com.fasterxml.jackson.annotation.JsonProperty("place_id")
        private String placeId;
        @com.fasterxml.jackson.annotation.JsonProperty("price_level")
        private Integer priceLevel;
        @com.fasterxml.jackson.annotation.JsonProperty("rating")
        private Double rating;
        @com.fasterxml.jackson.annotation.JsonProperty("user_ratings_total")
        private Integer userRatingsTotal;
        @com.fasterxml.jackson.annotation.JsonProperty("vicinity")
        private String vicinity;

        public String getBusinessStatus() {
            return businessStatus;
        }

        public void setBusinessStatus(String businessStatus) {
            this.businessStatus = businessStatus;
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

        public String getIconBackgroundColor() {
            return iconBackgroundColor;
        }

        public void setIconBackgroundColor(String iconBackgroundColor) {
            this.iconBackgroundColor = iconBackgroundColor;
        }

        public String getIconMaskBaseUri() {
            return iconMaskBaseUri;
        }

        public void setIconMaskBaseUri(String iconMaskBaseUri) {
            this.iconMaskBaseUri = iconMaskBaseUri;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public OpeningHoursDTO getOpeningHours() {
            return openingHours;
        }

        public void setOpeningHours(OpeningHoursDTO openingHours) {
            this.openingHours = openingHours;
        }

        public List<PhotosDTO> getPhotos() {
            return photos;
        }

        public void setPhotos(List<PhotosDTO> photos) {
            this.photos = photos;
        }

        public String getPlaceId() {
            return placeId;
        }

        public void setPlaceId(String placeId) {
            this.placeId = placeId;
        }

        public Integer getPriceLevel() {
            return priceLevel;
        }

        public void setPriceLevel(Integer priceLevel) {
            this.priceLevel = priceLevel;
        }

        public Double getRating() {
            return rating;
        }

        public void setRating(Double rating) {
            this.rating = rating;
        }

        public Integer getUserRatingsTotal() {
            return userRatingsTotal;
        }

        public void setUserRatingsTotal(Integer userRatingsTotal) {
            this.userRatingsTotal = userRatingsTotal;
        }

        public String getVicinity() {
            return vicinity;
        }

        public void setVicinity(String vicinity) {
            this.vicinity = vicinity;
        }

        public static class GeometryDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("location")
            private ResultsDTO.GeometryDTO.LocationDTO location;

            public LocationDTO getLocation() {
                return location;
            }

            public void setLocation(LocationDTO location) {
                this.location = location;
            }

            public static class LocationDTO {
                @com.fasterxml.jackson.annotation.JsonProperty("lat")
                private Double lat;
                @com.fasterxml.jackson.annotation.JsonProperty("lng")
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
            @com.fasterxml.jackson.annotation.JsonProperty("open_now")
            private Boolean openNow;

            public Boolean getOpenNow() {
                return openNow;
            }

            public void setOpenNow(Boolean openNow) {
                this.openNow = openNow;
            }
        }

        public static class PhotosDTO {
            @com.fasterxml.jackson.annotation.JsonProperty("height")
            private Integer height;
            @com.fasterxml.jackson.annotation.JsonProperty("html_attributions")
            private List<String> htmlAttributions;
            @com.fasterxml.jackson.annotation.JsonProperty("photo_reference")
            private String photoReference;
            @com.fasterxml.jackson.annotation.JsonProperty("width")
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

            public String getPhotoReference() {
                return photoReference;
            }

            public void setPhotoReference(String photoReference) {
                this.photoReference = photoReference;
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
