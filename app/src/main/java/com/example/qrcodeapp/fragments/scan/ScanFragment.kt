package com.example.qrcodeapp.fragments.scan

import android.app.Activity.RESULT_OK
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.qrcodeapp.R
import com.example.qrcodeapp.model.Scan
import com.google.zxing.integration.android.IntentIntegrator
import com.journeyapps.barcodescanner.ScanOptions
import kotlinx.android.synthetic.main.fragment_scan.view.*


class ScanFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_scan, container, false)

        view.button_scan.setOnClickListener {

            val integrator = IntentIntegrator.forSupportFragment(this)
            integrator.setDesiredBarcodeFormats(IntentIntegrator.PRODUCT_CODE_TYPES)
            integrator.setPrompt("Scan a barcode")
            integrator.setCameraId(0)  // use a specific camera of the device
            integrator.setBeepEnabled(false)
            integrator.setOrientationLocked(false)
            integrator.initiateScan()



            /*val integrator = IntentIntegrator.forSupportFragment(this@ScanFragment)

            integrator.setOrientationLocked(false)
            integrator.setPrompt("Scan a barcode")
            integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
            integrator.setBeepEnabled(false)
            integrator.initiateScan()*/
        }
        return view
    }

    private fun addScan(scan: Scan) {

    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(resultCode == RESULT_OK) {
            val resultScan = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
            if (resultScan != null) {
                if (resultScan.contents == null) {
                    Toast.makeText(requireContext(), "отменено", Toast.LENGTH_LONG
                    ).show()
                } else {
                    Toast.makeText(requireContext(), "Сканирование: " + resultScan.contents, Toast.LENGTH_LONG
                    ).show()
                }
            } else {
                super.onActivityResult(requestCode, resultCode, data)
            }
        }
    }




}