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
import kotlinx.coroutines.launch
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }

    private val retrofit by lazy {
        RetrofitHelper.retrofit
    }

    private lateinit var galeriaAdapter: GaleriaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        galeriaAdapter = GaleriaAdapter()
        galeriaAdapter.adicionarImagens(
            listOf("https://img.freepik.com/fotos-gratis/gatos-bonitos-relaxando-dentro-de-casa_23-2150692683.jpg",
                "https://img.freepik.com/fotos-gratis/gatos-bonitos-relaxando-dentro-de-casa_23-2150692683.jpg",
                "https://img.freepik.com/fotos-gratis/gatos-bonitos-relaxando-dentro-de-casa_23-2150692683.jpg",
                "https://img.freepik.com/fotos-gratis/gatos-bonitos-relaxando-dentro-de-casa_23-2150692683.jpg")
        )
        binding.rvGaleria.adapter = galeriaAdapter
        binding.rvGaleria.layoutManager = GridLayoutManager(this, 3, RecyclerView.VERTICAL, false)


    }


    private suspend fun recuperarImagem() {

        var retorno: Response<ImagensRespostas>? = null

        try{
            val chamadaAPI = retrofit.recuperarImagens("cats")
            retorno = chamadaAPI

        }catch (e: Exception) {
            e.printStackTrace()
            Log.i("info_imgur", "Erro CODE:")
        }

        if(retorno != null) {

            if(retorno.isSuccessful){

                val listaImage = retorno.body()
                val listaImagens = listaImage?.listaImagens

                var resultado = ""
                listaImagens?.forEach{imagens ->
                    val image = imagens.link
                    val listaResultado = "Imagem: $image"

                    resultado += listaResultado
                    Log.i("info_imgur", "$resultado")
                }

            } else {
                Log.i("info_imgur", "${retorno.code()}")
            }
        }
    }
}