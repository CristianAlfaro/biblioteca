package com.example.android.roomwordssample.Fragments

import android.content.Context
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.roomwordssample.Adapter.BookListAdapter
import com.example.android.roomwordssample.ViewModel.BookViewModel
import com.example.android.roomwordssample.R
import com.google.android.material.floatingactionbutton.FloatingActionButton
import kotlinx.android.synthetic.main.content_main.view.*

private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class MainFragment : Fragment(), BookFragment.OnFragmentInteractionListener, FavoriteFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    private var param1: String? = null
    private var param2: String? = null
    private var listener: OnFragmentInteractionListener? = null

    private lateinit var favoriteFragment: FavoriteFragment
    private val newWordActivityRequestCode = 1
    private lateinit var bookViewModel: BookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        var view = inflater.inflate(R.layout.fragment_main, container, false)
        initAll(view)
        return view
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
            throw RuntimeException(context.toString() + " must implement OnFragmentInteractionListener") as Throwable
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
        fun newInstance(param1: String, param2: String) =
                MainFragment().apply {
                    arguments = Bundle().apply {
                        putString(ARG_PARAM1, param1)
                        putString(ARG_PARAM2, param2)
                    }
                }
    }

    fun initAll(view: View){


        val recyclerView = view.recyclerview
        val adapter = object : BookListAdapter(view.context){
            override fun addListener(holder:WordViewHolder,titulo: String, caratula: Int, favorito: String, autor: String, editorial: String, resumen: String, tags: String) {
                holder.book_container.setOnClickListener {
                    val bookFragment = BookFragment.newInstance(titulo, autor, favorito, caratula,editorial, resumen, tags)
                    fragmentManager!!.beginTransaction().replace(R.id.main_fragment_content,bookFragment).addToBackStack("").commit()
                }
            }

        }
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(view.context)

        // Get a new or existing ViewModel from the ViewModelProvider.
        bookViewModel = ViewModelProviders.of(this).get(BookViewModel::class.java)

        // Add an observer on the LiveData returned by getAlphabetizedWords.
        // The onChanged() method fires when the observed data changes and the activity is
        // in the foreground.
        bookViewModel.allWords.observe(this, Observer { words ->
            // Update the cached copy of the words in the adapter.
            words?.let { adapter.setWords(it) }
        })

        val fab: FloatingActionButton = view.findViewById(R.id.fab)
        fab.setOnClickListener {
            /*val intent = Intent(this@MainActivity, Main2Activity::class.java)
            startActivityForResult(intent, newWordActivityRequestCode)*/
            favoriteFragment = FavoriteFragment()
            fragmentManager!!.beginTransaction().add(R.id.main_fragment_content, favoriteFragment).addToBackStack("").commit()
        }

    }

}
