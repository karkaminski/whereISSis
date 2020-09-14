package pl.karkaminski.whereissis.view

import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProviders
import org.osmdroid.api.IMapController
import org.osmdroid.config.Configuration
import org.osmdroid.tileprovider.tilesource.TileSourceFactory
import org.osmdroid.util.GeoPoint
import org.osmdroid.views.MapView
import org.osmdroid.views.overlay.Marker
import pl.karkaminski.whereissis.R
import pl.karkaminski.whereissis.viewmodel.IssNowViewModel


class MainActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "Debug: MainActivity"
    }

    //Map
    private lateinit var mapView: MapView
    private lateinit var mapController: IMapController

    //ViewModel
    private lateinit var issNowViewModel: IssNowViewModel


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        Log.i(TAG, "onCreate: ")

        //required by OSMDroid
        val ctx = applicationContext
        Configuration.getInstance().load(ctx, PreferenceManager.getDefaultSharedPreferences(ctx))

        //Setup MapView
        mapView = findViewById(R.id.mapView)
        mapView.setTileSource(TileSourceFactory.MAPNIK)
        mapController = mapView.controller
        mapController.setZoom(5.0)

        issNowViewModel = ViewModelProviders.of(this).get(IssNowViewModel::class.java)
        issNowViewModel.issNowResponseJSON.observe(this,
            { t ->
                refreshPositionOnMap(
                    t?.issPosition?.latitude!!.toDouble(),
                    t?.issPosition?.longitude!!.toDouble()
                )
            })
        issNowViewModel.startRefreshing()
    }

    /**
     * Refreshing ISS-icon position on map
     */
    private fun refreshPositionOnMap(lat: Double, lon: Double) {
        mapView.overlays.clear()

        var issLocationPoint = GeoPoint(lat, lon)
        var marker = Marker(mapView)
        marker.position = issLocationPoint
        marker.icon = getDrawable(R.drawable.ic_iss)
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_CENTER)
        mapView.overlays.add(marker)
        mapController.setCenter(issLocationPoint)
    }
}