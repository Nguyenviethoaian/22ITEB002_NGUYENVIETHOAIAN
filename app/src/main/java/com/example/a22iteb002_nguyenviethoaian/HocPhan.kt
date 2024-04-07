package com.example.a22iteb002_nguyenviethoaian

data class HocPhan(var id:Int,var name: String, var number: String, var semester: String) {
    private var _id: Int
        get() = _id
        set(value) {
            _id=value
        }
    private var _name: String
        get() = _name
        set(value) {
            _name = value
        }
    private var _number: String
        get() = _number
        set(value) {
            _number = value
        }
    private var _semester : String
        get() = _semester
        set(value) {
            _semester = value
        }

}