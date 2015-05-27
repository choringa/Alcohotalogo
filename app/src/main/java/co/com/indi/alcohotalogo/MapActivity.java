package co.com.indi.alcohotalogo;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Canvas;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.widget.Toast;

import org.osmdroid.bonuspack.overlays.Marker;
import org.osmdroid.tileprovider.tilesource.TileSourceFactory;
import org.osmdroid.util.GeoPoint;
import org.osmdroid.views.MapController;
import org.osmdroid.views.MapView;
import org.osmdroid.views.Projection;
import org.osmdroid.views.overlay.Overlay;


public class MapActivity extends ActionBarActivity implements LocationListener{

    //---------------------------
    //ATRIBUTOS
    //---------------------------

    /**
     * Atributo de open street map
     */
    private MapView osm;

    /**
     * Atributo de mapController para el osm
     */
    private MapController mapController;

    /**
     * Atributo del location manager para ver la locacion del usuario
     */
    private LocationManager locationManager;

    /**
     * Atributo que maneja la longitud y la latitud cuando se hace tap en el map
     */
    private String longitude,latitude;


    private Overlay touchOverlay;

    //---------------------------
    //CONSTANTES
    //---------------------------

    /**
     * Constante del tag
     */
    private final static String TAG = "MapActivity";

    //---------------------------
    //METODOS
    //---------------------------

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);

        osm = (MapView) findViewById(R.id.MapView);
        osm.setTileSource(TileSourceFactory.MAPQUESTOSM);
        osm.setBuiltInZoomControls(true);
        osm.setMultiTouchControls(true);

        mapController = (MapController) osm.getController();
        mapController.setZoom(40);

        final GeoPoint geoPoint = new GeoPoint(4.62710,-74.06844);
        mapController.animateTo(geoPoint);
        addMarkerUser(geoPoint);

        locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 10000, 100, this);

        touchOverlay = new Overlay(this) {
            @Override
            protected void draw(Canvas canvas, MapView mapView, boolean b) {

            }
            @Override
            public boolean onSingleTapConfirmed(final MotionEvent e, final MapView mapView) {



                Projection proj = mapView.getProjection();
                GeoPoint p = (GeoPoint) proj.fromPixels((int) e.getX(), (int) e.getY());
                proj = mapView.getProjection();
                GeoPoint loc = (GeoPoint) proj.fromPixels((int) e.getX(), (int) e.getY());
                String longitude = Double
                        .toString(((double) loc.getLongitudeE6()) / 1000000);
                String latitude = Double
                        .toString(((double) loc.getLatitudeE6()) / 1000000);
                Toast toast = Toast.makeText(getApplicationContext(),
                        "Longitude: "
                                + longitude + " Latitude: " + latitude, Toast.LENGTH_SHORT);
                toast.show();
                createDialogChooserMarkers(loc);
                return true;
            }
        };
        osm.getOverlays().add(touchOverlay);
    }

    /**
     * Metodo que añade marcador de la ubicacion del usuario al mapa
     * @param geoPoint
     */
    public void addMarkerUser(GeoPoint geoPoint){
        Marker marker = new Marker(osm);
        marker.setPosition(geoPoint);
        marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(R.drawable.marker_user4));

        osm.getOverlays().clear();
        osm.getOverlays().add(marker);
        if(touchOverlay != null)
            osm.getOverlays().add(touchOverlay);
        osm.invalidate();
    }

    /**
     * Metodo que agrega una tiena al mapa
     * @param geoPoint
     */
    public  void addMarker(GeoPoint geoPoint){
        Marker marker = new Marker(osm);
        marker.setPosition(geoPoint);
        //marker.setAnchor(Marker.ANCHOR_CENTER, Marker.ANCHOR_BOTTOM);
        marker.setIcon(getResources().getDrawable(R.drawable.marker_tienda2));
        marker.setOnMarkerClickListener(new Marker.OnMarkerClickListener() {
            @Override
            public boolean onMarkerClick(Marker marker, MapView mapView) {
                GeoPoint geo = marker.getPosition();
                Log.d(TAG, "espichado: " + geo.getAltitude() + "," +geo.getLatitude());
                return false;
            }
        });
        osm.getOverlays().add(marker);
        osm.invalidate();
    }

    private void createDialogChooserMarkers(final GeoPoint geoPoint)
    {
        final String[] options=getResources().getStringArray(R.array.options_marker);

        CharSequence[] csOptions =options;
        AlertDialog.Builder builder = new AlertDialog.Builder(MapActivity.this);
        builder.setTitle(getString(R.string.options));
        builder.setItems(csOptions, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0: // agregarPunto
                    {
                        addMarker(geoPoint);
                        break;
                    }
                    case 1: // salir
                    {
                        break;
                    }

                }
            }
        });

        AlertDialog alert = builder.create();
        alert.show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_map, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    //-------------------------------------
    // METODOS IMPLEMENTS LOCATIONLISTENER
    //-------------------------------------

    @Override
    public void onLocationChanged(Location location) {
        //Si el usuario se mueve coge la nueva locacion y la hubica en el mapa
        GeoPoint geoPoint = new GeoPoint(location.getLatitude(), location.getLongitude());
        mapController.animateTo(geoPoint);
        addMarkerUser(geoPoint);
    }

    @Override
    public void onStatusChanged(String provider, int status, Bundle extras) {

    }

    @Override
    public void onProviderEnabled(String provider) {

    }

    @Override
    public void onProviderDisabled(String provider) {

    }

    /**
     * Ayuda a reducir el consumo de bateria con la geolocalizacion
     */
    @Override
    public void onDestroy(){
        super.onDestroy();
        if(locationManager != null)
            locationManager.removeUpdates(this);
    }




}
