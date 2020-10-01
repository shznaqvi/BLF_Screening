package edu.aku.hassannaqvi.blf_screening.ui.sections

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.validatorcrawler.aliazaz.Clear
import com.validatorcrawler.aliazaz.Validator.Companion.emptyCheckingContainer
import edu.aku.hassannaqvi.blf_screening.CONSTANTS
import edu.aku.hassannaqvi.blf_screening.R
import edu.aku.hassannaqvi.blf_screening.contracts.FormsContract
import edu.aku.hassannaqvi.blf_screening.core.MainApp.appInfo
import edu.aku.hassannaqvi.blf_screening.core.MainApp.form
import edu.aku.hassannaqvi.blf_screening.databinding.ActivitySectionDBinding
import edu.aku.hassannaqvi.blf_screening.utils.contextBackActivity
import edu.aku.hassannaqvi.blf_screening.utils.openFormEndActivity
import edu.aku.hassannaqvi.blf_screening.utils.showTooltip
import org.json.JSONException

class SectionDActivity : AppCompatActivity() {
    lateinit var bi: ActivitySectionDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bi = DataBindingUtil.setContentView(this, R.layout.activity_section_d)
        bi.callback = this
        bi.form = form
        setupSkips()
    }

    private fun setupSkips() {
        bi.d02.setOnCheckedChangeListener { radioGroup: RadioGroup, i: Int ->
            if (i == bi.d0202.id) {
                Clear.clearAllFields(bi.fldGrpCVd03)
            }
        }
    }

    @Throws(JSONException::class)
    private fun saveDraft() {

        form.d01 = bi.d01.text.toString()
        form.d02 = when {
            bi.d0201.isChecked -> "1"
            bi.d0202.isChecked -> "2"
            else -> "-1"
        }
        form.d03 = when {
            bi.d0301.isChecked -> "1"
            bi.d0302.isChecked -> "2"
            bi.d0303.isChecked -> "3"
            bi.d0304.isChecked -> "4"
            bi.d0305.isChecked -> "5"
            bi.d03096.isChecked -> "96"
            else -> "-1"
        }
        form.d04 = when {
            bi.d0401.isChecked -> "1"
            bi.d0402.isChecked -> "2"
            else -> "-1"
        }
        form.d05 = when {
            bi.d0501.isChecked -> "1"
            bi.d0502.isChecked -> "2"
            else -> "-1"
        }

        /*  val json = JSONObject()
          json.put("d1", bi.d1.text.toString())
          json.put("d02", when {
              bi.d0201.isChecked -> "1"
              bi.d0202.isChecked -> "2"
              else -> "-1"
          })
          json.put("d03", when {
              bi.d0301.isChecked -> "1"
              bi.d0302.isChecked -> "2"
              bi.d0303.isChecked -> "3"
              bi.d0304.isChecked -> "4"
              bi.d0305.isChecked -> "5"
              bi.d03096.isChecked -> "96"
              else -> "-1"
          })
          json.put("d04", when {
              bi.d0401.isChecked -> "1"
              bi.d0402.isChecked -> "2"
              else -> "-1"
          })
          json.put("d05", when {
              bi.d0501.isChecked -> "1"
              bi.d0502.isChecked -> "2"
              else -> "-1"
          })

          MainApp.form.setsD(json.toString())*/
    }


    private fun formValidation(): Boolean {
        return emptyCheckingContainer(this, bi.fldGrpSectionD)
    }


    private fun updateDB(): Boolean {
        val db = appInfo.dbHelper
        val updcount = db.updatesFormColumn(FormsContract.FormsTable.COLUMN_SD, form.sDtoString())
        return if (updcount > 0) {
            true
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show()
            false
        }
    }


    fun BtnContinue(v: View) {
        if (!formValidation()) return
        try {
            saveDraft()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
        if (updateDB()) {
            finish()
            startActivity(Intent(this, SectionEActivity::class.java))
        } else {
            Toast.makeText(this, "Sorry. You can't go further.\n Please contact IT Team (Failed to update DB)", Toast.LENGTH_SHORT).show()
        }
    }


    fun BtnEnd(v: View) {
        openFormEndActivity(this, CONSTANTS.FSTATUS_END_FLAG, 2)
    }


    override fun onBackPressed() {
        contextBackActivity(this)
    }

    fun showTooltipView(view: View?) {
        showTooltip(this, view!!)
    }
}