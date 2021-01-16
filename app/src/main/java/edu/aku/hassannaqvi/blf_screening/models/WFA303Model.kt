package edu.aku.hassannaqvi.blf_screening.models

import java.util.ArrayList

data class WFA303Model(var sysdate: String, var wfa30301: String? = "-1", var wfa30302: String? = "-1", var wfa30303: String? = "-1", var wfa30304: String? = "-1")

data class SubModel(var disease: String, var submodel: ArrayList<WFA303Model>)