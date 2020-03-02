package com.example.implicitintentcombos

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.core.app.ShareCompat

class MainActivity : AppCompatActivity() {

    lateinit var websiteEditText: EditText
    lateinit var locationEditText: EditText
    lateinit var shareTextEditText: EditText
    lateinit var openWebsiteButton: Button
    lateinit var searchLocationButton: Button
    lateinit var shareTextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        websiteEditText = findViewById(R.id.website_editText)
        locationEditText = findViewById(R.id.location_editText)
        shareTextEditText = findViewById(R.id.shareText_editText)
        openWebsiteButton = findViewById(R.id.openWebsite_button)
        searchLocationButton = findViewById(R.id.searchLocation_button)
        shareTextButton = findViewById(R.id.shareText_button)
        /**
         * get current View
         * */
        val currentView: View = findViewById(android.R.id.content)

        openWebsiteButton.setOnClickListener {
            openWebsite()
        }

        searchLocationButton.setOnClickListener {
            searchLocation()
        }

        shareTextButton.setOnClickListener {
            shareText()
        }
    }

    /**
     * [ACTION_VIEW] (to view the given data)
     * [ACTION_EDIT] (to edit the given data)
     * [ACTION_DIAL] (to dial a phone number)
     * */
    private fun openWebsite() {
        /**
         * http/https is required
         * */
        val url = websiteEditText.text.toString()
        val website = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, website)

        /**
         * find an activity that can handle this intent
         * */
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Can't  open website!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchLocation() {
        val location = locationEditText.text.toString()
        val address = Uri.parse("geo:0,0?q=${location}")
        val intent = Intent(Intent.ACTION_VIEW, address)
        /**
         * find an activity that can handle this intent
         * */
        if (intent.resolveActivity(packageManager) != null) {
            startActivity(intent)
        } else {
            Toast.makeText(this, "Can't search Location!", Toast.LENGTH_SHORT).show()
        }
    }

    private fun shareText() {
        val text = shareTextEditText.text.toString()
        ShareCompat.IntentBuilder.from(this)
            .setType("text/plain")
            .setChooserTitle("Share this text with: ")
            .setText(text)
            .startChooser()
    }


}
