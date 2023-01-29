package dev.android.appreservas

import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.webkit.*
import android.widget.SearchView
import kotlinx.android.synthetic.main.activity_busquedas.*

class BusquedasActivity : AppCompatActivity() {

    private val BASE_URL = "https://google.com"
    private val SEARCH_PATH = "/search?q="

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_busquedas)
        Navegacion()
    }

    private fun Navegacion(){

        //Refresh
        srNavegacion.setOnRefreshListener {
            wvNavegacion.reload()
        }

        //SearchView
        svNavegacion.setOnQueryTextListener(object : SearchView.OnQueryTextListener{

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

            override fun onQueryTextSubmit(cadena: String?): Boolean {
                cadena?.let {
                    if (URLUtil.isValidUrl(it)){
                        //Url válida
                        wvNavegacion.loadUrl(it)
                    } else {
                        wvNavegacion.loadUrl("$BASE_URL$SEARCH_PATH$it")
                    }
                }
                return false
            }
        })


        //WebView
        wvNavegacion.webChromeClient = object:WebChromeClient(){}

        wvNavegacion.webViewClient = object:WebViewClient(){
            //La aplicación controla la carga de las url
            override fun shouldOverrideUrlLoading(
                view: WebView?,
                request: WebResourceRequest?
            ): Boolean {
                return false
            }

            override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
                super.onPageStarted(view, url, favicon)
                svNavegacion.setQuery(url, false)
                srNavegacion.isRefreshing = true
            }

            override fun onPageFinished(view: WebView?, url: String?) {
                super.onPageFinished(view, url)
                srNavegacion.isRefreshing = false
            }
        }

        val settings:WebSettings = wvNavegacion.settings
        settings.javaScriptEnabled = true
        wvNavegacion.loadUrl(BASE_URL)
    }

    override fun onBackPressed() {
        if (wvNavegacion.canGoBack()){
            wvNavegacion.goBack()
        } else {
            super.onBackPressed()
        }
    }
}