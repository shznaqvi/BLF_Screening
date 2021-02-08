package edu.aku.hassannaqvi.blf_screening.models

import java.util.ArrayList

data class WFA303Model(var sysdate: String, var wfa30301: String? = "-1", var wfa30302: String? = "-1", var wfa30303: String? = "-1", var wfa30304: String? = "-1")
data class SubModel(var disease: String, var submodel: ArrayList<WFA303Model>)


data class WFB108(var sysdate: String, var wfb1081a: String? = "-1", var wfb1081b: String? = "-1", var wfb1081c: String? = "-1", var wfb1081d: String? = "-1", var wfb1081d5: String? = "-1", var wfb1081d96: String? = "-1")
data class WFBSubModel(var wfb108: String, var submodel2: ArrayList<WFB108>)