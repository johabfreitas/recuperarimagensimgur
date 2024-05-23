package br.com.johabfreitas.recuperarimagensimgur

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import br.com.johabfreitas.recuperarimagensimgur.adapter.GaleriaAdapter
import br.com.johabfreitas.recuperarimagensimgur.api.RetrofitHelper
import br.com.johabfreitas.recuperarimagensimgur.databinding.ActivityMainBinding
import br.com.johabfreitas.recuperarimagensimgur.model.ImagensRespostas
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val imgurAPI by lazy {
        RetrofitHelper.imgurAPI
    }

    private var job: Job? = null

    private lateinit var galeriaAdapter: GaleriaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        galeriaAdapter = GaleriaAdapter()
        binding.rvGaleria.adapter = galeriaAdapter
        binding.rvGaleria.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)


    }

    override fun onStart() {
        super.onStart()
        recuperarImagensAPI()
    }

    override fun onDestroy() {
        super.onDestroy()
        job?.cancel()
    }

    fun recuperarImagensAPI(){

        job = CoroutineScope(Dispatchers.IO).launch {
            var response: Response<ImagensRespostas>? = null

            try{
                response = imgurAPI.recuperarImagensGaleria("cats")

            }catch(e: Exception){
                e.printStackTrace()
            }

            if(response != null && response.isSuccessful){

                var resultado = response.body()
                if(resultado != null){

                    var listaDados = resultado.data

                    var listaUrlImagens = mutableListOf<String>()

                    listaDados.forEach{dados ->
                        val imagem = dados.images[0]
                        val tipo = imagem.type
                        if(tipo == "image/jpeg"){
                            listaUrlImagens.add(imagem.link)
                        }
                    }

                    withContext(Dispatchers.Main){
                        galeriaAdapter.adicionarImagens(listaUrlImagens)
                    }

                }

            } else {
                Log.i("info_imgur", "Erro ao recuperar CODE: ${response?.code()} ")
            }
        }
    }
}