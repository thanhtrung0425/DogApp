package com.example.dogapp.view;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.GridLayoutManager;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.dogapp.MainActivity;
import com.example.dogapp.R;
import com.example.dogapp.databinding.FragmentListBinding;
import com.example.dogapp.model.DogBreed;
import com.example.dogapp.viewmoodel.DogAdapter;
import com.example.dogapp.viewmoodel.DogsApiService;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.observers.DisposableSingleObserver;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class ListFragment extends Fragment {

    private DogsApiService apiService;

    private ArrayList<DogBreed> dogsList;
    private DogAdapter dogAdapter;

    private FragmentListBinding binding;
    private View view;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentListBinding.inflate(inflater, container, false);
        final FragmentActivity getactivity = getActivity();
        dogsList = new ArrayList<>();
        apiService = new DogsApiService();
        apiService.getDogs()
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<List<DogBreed>>() {
                    @Override
                    public void onSuccess(@NonNull List<DogBreed> dogBreeds) {
                        Log.d("DEBUG", "Success");
                        for (DogBreed dog: dogBreeds){
                            Log.d("Debug", ""+ dog.getUrl());
                            dogsList.add(new DogBreed(dog.getId(), dog.getName(), dog.getLifeSpan(),
                                    dog.getOrigin(), dog.getUrl(), dog.getBredFor(), dog.getBreedGroup(),
                                    dog.getTemperament(), dog.getHeight(), dog.getWeight()));
                            dogAdapter = new DogAdapter(dogsList);
                            binding.rvDogs.setLayoutManager(new GridLayoutManager(getContext(), 2));
                            binding.rvDogs.setAdapter(dogAdapter);
                            dogAdapter.notifyDataSetChanged();
                        }
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        Log.d("DEBUG", "Failed: " + e.getMessage());
                    }
                });

        return binding.getRoot();

    }

}