package Models;

import java.sql.Struct;

public class WeatherData {
    private Location location;
    private Current current;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

    @Override
    public String toString() {
        return "WeatherResponse{" +
                "location=" + location +
                ", current=" + current +
                '}';
    }

    public static class Location {
        private String name;
        private String region;
        private String country;
        private double lat;
        private double lon;
        private String tz_id;
        private long localtime_epoch;
        private String localtime;

        public String getName() {
            return name;
        }

        public String getRegion() {
            return region;
        }

        public String getCountry() {
            return country;
        }

        public double getLat() {
            return lat;
        }

        public double getLon() {
            return lon;
        }

        public String getTz_id() {
            return tz_id;
        }

        public long getLocaltime_epoch() {
            return localtime_epoch;
        }

        public String getLocaltime() {
            return localtime;
        }

        // Getter and Setter methods for the Location fields

        @Override
        public String toString() {
            return "Location{" +
                    "name='" + name + '\'' +
                    ", region='" + region + '\'' +
                    ", country='" + country + '\'' +
                    ", lat=" + lat +
                    ", lon=" + lon +
                    ", tz_id='" + tz_id + '\'' +
                    ", localtime_epoch=" + localtime_epoch +
                    ", localtime='" + localtime + '\'' +
                    '}';
        }
    }

    public static class Current {
        private long last_updated_epoch;
        private String last_updated;
        private double temp_c;
        private double temp_f;
        private int is_day;
        private Condition condition;
        private double wind_mph;
        private double wind_kph;
        private int wind_degree;
        private String wind_dir;
        private double pressure_mb;
        private double pressure_in;
        private double precip_mm;
        private double precip_in;
        private int humidity;
        private int cloud;
        private double feelslike_c;
        private double feelslike_f;
        private double vis_km;
        private double vis_miles;
        private double uv;
        private double gust_mph;
        private double gust_kph;


        public long getLast_updated_epoch() {
            return last_updated_epoch;
        }

        public String getLast_updated() {
            return last_updated;
        }

        public double getTemp_c() {
            return temp_c;
        }

        public double getTemp_f() {
            return temp_f;
        }

        public int getIs_day() {
            return is_day;
        }

        public Condition getCondition() {
            return condition;
        }

        public double getWind_mph() {
            return wind_mph;
        }

        public double getWind_kph() {
            return wind_kph;
        }

        public int getWind_degree() {
            return wind_degree;
        }

        public String getWind_dir() {
            return wind_dir;
        }

        public double getPressure_mb() {
            return pressure_mb;
        }

        public double getPressure_in() {
            return pressure_in;
        }

        public double getPrecip_mm() {
            return precip_mm;
        }

        public double getPrecip_in() {
            return precip_in;
        }

        public int getHumidity() {
            return humidity;
        }

        public int getCloud() {
            return cloud;
        }

        public double getFeelslike_c() {
            return feelslike_c;
        }

        public double getFeelslike_f() {
            return feelslike_f;
        }

        public double getVis_km() {
            return vis_km;
        }

        public double getVis_miles() {
            return vis_miles;
        }

        public double getUv() {
            return uv;
        }

        public double getGust_mph() {
            return gust_mph;
        }

        public double getGust_kph() {
            return gust_kph;
        }

        // Getter and Setter methods for the Current fields

        @Override
        public String toString() {
            return "Current{" +
                    "last_updated_epoch=" + last_updated_epoch +
                    ", last_updated='" + last_updated + '\'' +
                    ", temp_c=" + temp_c +
                    ", temp_f=" + temp_f +
                    ", is_day=" + is_day +
                    ", condition=" + condition +
                    ", wind_mph=" + wind_mph +
                    ", wind_kph=" + wind_kph +
                    ", wind_degree=" + wind_degree +
                    ", wind_dir='" + wind_dir + '\'' +
                    ", pressure_mb=" + pressure_mb +
                    ", pressure_in=" + pressure_in +
                    ", precip_mm=" + precip_mm +
                    ", precip_in=" + precip_in +
                    ", humidity=" + humidity +
                    ", cloud=" + cloud +
                    ", feelslike_c=" + feelslike_c +
                    ", feelslike_f=" + feelslike_f +
                    ", vis_km=" + vis_km +
                    ", vis_miles=" + vis_miles +
                    ", uv=" + uv +
                    ", gust_mph=" + gust_mph +
                    ", gust_kph=" + gust_kph +
                    '}';
        }
    }

    public static class Condition {
        private String text;
        private String icon;
        private int code;

        // Getter and Setter methods for the Condition fields

        public String getText() {
            return text;
        }

        public String getIcon() {
            return icon;
        }

        public int getCode() {
            return code;
        }

        @Override
        public String toString() {
            return "Condition{" +
                    "text='" + text + '\'' +
                    ", icon='" + icon + '\'' +
                    ", code=" + code +
                    '}';
        }
    }
}