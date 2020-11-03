package by.it.academy.storage

import android.Manifest
import android.content.Context
import android.content.pm.PackageManager
import android.os.Bundle
import android.os.Environment
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.viewCaseCheckBox
import kotlinx.android.synthetic.main.activity_main.viewSaveButton
import kotlinx.android.synthetic.main.activity_main.viewText
import java.io.File
import java.io.FileOutputStream
import java.util.Locale

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val isUpperCase = loadCheckBoxState()

        loadLastText()?.let {
            val actualText = if (isUpperCase) {
                it.toUpperCase()
            } else {
                it.toLowerCase()
            }
            viewText.setText(actualText)
        }

        viewCaseCheckBox.isChecked = isUpperCase
        viewCaseCheckBox.setOnCheckedChangeListener { _, isChecked ->
            revertTextCase(isChecked)
            saveCheckBoxState(isChecked)
        }

        viewSaveButton.setOnClickListener {
            saveLastText(viewText.text.toString())
            logText(viewText.text.toString())
        }
    }

    private fun revertTextCase(isChecked: Boolean) {
        val text = viewText.text.toString()
        if (isChecked) {
            viewText.setText(text.toUpperCase(Locale.getDefault()))
        } else {
            viewText.setText(text.toLowerCase(Locale.getDefault()))
        }
    }

    private fun saveCheckBoxState(isChecked: Boolean) {
        val sharedPrefs = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putBoolean("KEY_IS_CHECKED", isChecked)
        editor.apply()
    }

    private fun loadCheckBoxState(): Boolean {
        val sharedPrefs = getPreferences(Context.MODE_PRIVATE)
        return sharedPrefs.getBoolean("KEY_IS_CHECKED", false)
    }

    private fun saveLastText(text: String) {
        val sharedPrefs = getPreferences(Context.MODE_PRIVATE)
        val editor = sharedPrefs.edit()
        editor.putString("KEY_LAST_TEXT", text)
        editor.apply()
    }

    private fun loadLastText(): String? {
        val sharedPrefs = getPreferences(Context.MODE_PRIVATE)
        return sharedPrefs.getString("KEY_LAST_TEXT", "")
    }

    private fun logText(text: String) {
//        getFilesDir() - Java
//        getCacheDir() - Java
//        getDataDir()
        FileOutputStream(File(filesDir, "text.txt"), true)
            .bufferedWriter()
            .use { out ->
                out.append(text)
                out.newLine()
            }
    }

    private fun isExternalStorageGranted(): Boolean {
        return checkSelfPermission(android.Manifest.permission.MANAGE_EXTERNAL_STORAGE) ==
            PackageManager.PERMISSION_GRANTED
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    private fun askPermission() {
        requestPermissions(
            arrayOf(
                Manifest.permission.MANAGE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION
            ),
            1000
        )
    }

    private fun isExternalStorageExists() =
        Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
}