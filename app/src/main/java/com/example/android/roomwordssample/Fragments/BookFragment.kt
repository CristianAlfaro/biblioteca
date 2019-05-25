package com.example.android.roomwordssample.Fragments

import android.content.Context
import android.content.res.Resources
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.android.roomwordssample.R
import kotlinx.android.synthetic.main.fragment_book.view.*


private const val TITULO = "titulo"
private const val CARATULA = "caratula"
private const val FAVORITO = "favorito"
private const val AUTOR = "autor"


class BookFragment : Fragment() {
    // TODO: Rename and change types of parameters
    private var titulo: String? = null
    private var favorito: String? = null
    private var autor: String? = null
    private var caratula: Int? = null
    private var listener: OnFragmentInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            titulo = it.getString(TITULO)
            favorito = it.getString(FAVORITO)
            autor = it.getString(AUTOR)
            caratula = it.getInt(CARATULA)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_book, container, false).apply {
            var titulo = arguments?.getString("titulo")
            var caratula = arguments!!.getInt("caratula")
            var autor = arguments?.getString("autor")
            var favorito = arguments?.getString("favorito")
            textViewAutor.text = autor
            textView.text = titulo
            iv_caratula.setImageResource(caratula)
            bt_favorite.text = favorito
        }
    }

    // TODO: Rename method, update argument and hook method into UI event
    fun onButtonPressed(uri: Uri) {
        listener?.onFragmentInteraction(uri)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnFragmentInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onFragmentInteraction(uri: Uri)
    }

    companion object {

        @JvmStatic
        fun newInstance(titulo: String, autor:String, favorito:String, caratula:Int) =
                BookFragment().apply {
                    arguments = Bundle().apply {
                        putString(TITULO, titulo)
                        putString(FAVORITO,favorito)
                        putString(AUTOR,autor)
                        putInt(CARATULA,caratula)
                    }
                }
    }
}
