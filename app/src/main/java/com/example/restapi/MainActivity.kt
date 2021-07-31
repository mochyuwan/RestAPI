package com.example.restapi

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.contract.ActivityResultContracts
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var rvdata: RecyclerView
    lateinit var apiService: ServiceInterface
    private var ambilData: ArrayList<KontakData> = arrayListOf()
    lateinit var btnadd: View
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        rvdata = findViewById(R.id.rv_data)
        btnadd = findViewById(R.id.btn_main_add)
        btnadd.setOnClickListener {
            startActivity(Intent(this, activity_add::class.java)) // pindah ke Add Activity
        }
/* Buat Pratikum 13 Tinggal hapus command trs yang atas hapus/command

    btnadd.setOnClickListener {
   Buat Pratikum 13 Tinggal hapus command trs yang atas hapus/command
    val pindah = Intent(this, MapsActivity::class.java) //pindah ke maps activity
    val bundle = Bundle()
    var lattitude : Double = -6.528635
    var longtitude : Double = 107.461614
    bundle.putDouble("lati",lattitude)
    bundle.putDouble("long",longtitude)
    pindah.putExtras(bundle)
    startActivity(pindah)
}
*/
apiService = Repository.getDataAPI().create(ServiceInterface::class.java)
apiService.getData().enqueue(object : Callback<List<KontakData>>{
    override fun onResponse(
            call: retrofit2.Call<List<KontakData>>,
            response: Response<List<KontakData>>
    ) {
        if (response.isSuccessful){
            val res = response.body()
            ambilData.addAll(res!!)
            rvdata.layoutManager = LinearLayoutManager(this@MainActivity)
            rvdata.adapter = KontakAdapter(ambilData)
        }
    }
    override fun onFailure(call: retrofit2.Call<List<KontakData>>, t: Throwable) {
    }
})

}
}
