package com.example.biletum.data.network.model.responses.events

import com.example.biletum.interactors.Interactor
import com.example.biletum.repository.DataRepository
import okhttp3.MultipartBody
import javax.inject.Inject

class UploadImageInteractor @Inject constructor(private val dataRepository: DataRepository) :
    Interactor<UploadImageInteractor.Params, ImageUploadResponse> {


    override suspend fun invoke(executeParams: Params): ImageUploadResponse {
        return dataRepository.imageUpload(executeParams.token, executeParams.file)

    }

    data class Params(val token: String, val file: MultipartBody.Part)

}