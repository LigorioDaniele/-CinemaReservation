package Common;

public class BaseItems {
    /**
        * BotListItem
        * @author Rodolfo Giampietro
        *
        */
    public static class TimeItemList {
            public int _id;
            public String _name; 
            public TimeItemList(){
                
            }
            public TimeItemList(int id, String name){
                    this._id = id;
                    this._name = name;
            }
            public int getId(){
                    return _id;
            }
            public String getName(){
                    return _name;
            }
    }
 
    public static class FilmsItemList {
        public int _id;
        public String _title; 
        public int _duration;
        public String _producer;
        public String _imageUrl;
        public FilmsItemList() {

        }
        public FilmsItemList(int id, String title, int duration, String producer, String imageUrl){
                this._id = id;
                this._title = title;
                this._duration = duration;
                this._producer = producer;
                this._imageUrl = imageUrl;
        }
        public int getId(){
                return _id;
        }
        public String getTitle(){
                return _title;
        }
        public int getDuration(){
                return _duration;
        }
        public String getProducer(){
                return _producer; 	
        }
        public String getImageUrl(){
                return _imageUrl; 	
        }

        public void setDuration(int _duration) {
            this._duration = _duration;
        }

        public void setId(int _id) {
            this._id = _id;
        }

        public void setImageUrl(String _imageUrl) {
            this._imageUrl = _imageUrl;
        }

        public void setProducer(String _producer) {
            this._producer = _producer;
        }

        public void setTitle(String _title) {
            this._title = _title;
        }

    }
    public static class UserItem {
        public int _id;
        public String _username, _password; 
        public boolean _isAdmin;
        public UserItem() {

        }
        public UserItem(String username, String password, boolean isAdmin){
            this._username = username;
            this._password = password;
            this._isAdmin = isAdmin;
        }
        public int getId(){
            return _id;
        }
        public String getUsername(){
            return _username;
        }
        public String getPassword(){
            return _password;
        }
        public boolean getIsAdmin() {
            return _isAdmin;
        }
        public boolean IsAdmin(){
            return _isAdmin;
        }

        public void setId(int _id) {
            this._id = _id;
        }

        public void setIsAdmin(boolean _isAdmin) {
            this._isAdmin = _isAdmin;
        }

        public void setPassword(String _password) {
            this._password = _password;
        }

        public void setUsername(String _username) {
            this._username = _username;
        }
        
    }

    public static class SeatedItemList {

        public int _user_id;
        public int _hall_id; 
        public int _time;
        public int _sit_x;
        public int _sit_y;
        public int _film_id;
        public String _date;
        public SeatedItemList() {

        }

        public SeatedItemList(int _user_id, int _hall_id, int _time, int _sit_x, int _sit_y, int _film_id, String _date) {
            this._user_id = _user_id;
            this._hall_id = _hall_id;
            this._time = _time;
            this._sit_x = _sit_x;
            this._sit_y = _sit_y;
            this._film_id = _film_id;
            this._date = _date;
        }

        public String getDate() {
            return _date;
        }

        public void setDate(String _date) {
            this._date = _date;
        }
        
        public void setUser_id(int _user_id) {
            this._user_id = _user_id;
        }

        public int getFilm_id() {
            return _film_id;
        }

        public int getSit_x() {
            return _sit_x;
        }

        public void setSit_x(int _sit_x) {
            this._sit_x = _sit_x;
        }

        public int getSit_y() {
            return _sit_y;
        }

        public void setSit_y(int _sit_y) {
            this._sit_y = _sit_y;
        }

        public int getTime() {
            return _time;
        }

        public void setTime(int _time) {
            this._time = _time;
        }

        public int getUser_id() {
            return _user_id;
        }

        public void setFilm_id(int film_id) {
            this._user_id = film_id;
        }

        public int getHall_id() {
            return _hall_id;
        }

        public void setHall_id(int hall_id) {
            this._hall_id = hall_id;
        }

    }
    
    public static class PalimpsestItemList {
        public int _day, _time, film_id, hall_id;

        public PalimpsestItemList() {
        }

        public PalimpsestItemList(int _day, int _time, int film_id, int hall_id) {
            this._day = _day;
            this._time = _time;
            this.film_id = film_id;
            this.hall_id = hall_id;
        }

        public int getDay() {
            return _day;
        }

        public void setDay(int _day) {
            this._day = _day;
        }

        public int getTime() {
            return _time;
        }

        public void setTime(int _time) {
            this._time = _time;
        }

        public int getFilm_id() {
            return film_id;
        }

        public void setFilm_id(int film_id) {
            this.film_id = film_id;
        }

        public int getHall_id() {
            return hall_id;
        }

        public void setHall_id(int hall_id) {
            this.hall_id = hall_id;
        }
    }
}
