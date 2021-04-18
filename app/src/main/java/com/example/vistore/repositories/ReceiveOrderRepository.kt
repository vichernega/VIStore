package com.example.vistore.repositories

import android.os.Environment
import android.util.Log
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.OrderObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.showToast
import com.itextpdf.text.Document
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

class ReceiveOrderRepository {

    fun saveOrder(userId: String){
        FirebaseObject.saveOrderInDB(userId)      // update order in db
    }

    /** BUG!!! Failure in saving file to emulator*/
    fun savePdf(){                  // create and save pdf receipt

        val receipt = Document()    // create document

        try {

            val path = APP_ACTIVITY.getExternalFilesDir(null).toString() + "/ViStore"       // get path
            val dir = File(path)                // create directory with path
            if (!dir.exists()){                 // create directory if it doesn't exists
                dir.mkdirs()
            }
            val fileName = "Receipt #${OrderObject.orderId}.pdf"        // fileName
            val file = File(dir, fileName)                              // create file with filename in directory
            val fileOutputStream = FileOutputStream(file)               // open stream

            PdfWriter.getInstance(receipt, fileOutputStream)            // write in file

            receipt.open()
            receipt.newPage()

            Log.d("PDFSAVE", "Path: " + path)


            /*//val filePath = "/storage/emulated/0/" + fileName
            *//*val filePath1 = APP_ACTIVITY.getExternalFilesDir(null)*//**//*.toString() + "/" + fileName*//*
            val filePath2 = APP_ACTIVITY.getExternalFilesDir(null).toString() + "/" + fileName
            val filePath3 = Environment.getExternalStorageDirectory().toString()*//* + "/" + fileName*//*


            *//*Log.d("PDFSAVE", "1: " + filePath1.toString())
            Log.d("PDFSAVE", "2: " + filePath2)*//*
            Log.d("PDFSAVE", "3: " + filePath3)

            PdfWriter.getIns
            val pdfDocument = PdfDocument(PdfWriter(filePath3))
            pdfDocument.addNewPage()
            pdfDocument.defaultPageSize = PageSize.A5
            val receipt = Document(pdfDocument)*/


            //add fields in pdfFile
            val orderId = Paragraph("Order ID: ${OrderObject.orderId}")
            val fullName = Paragraph("Full name: ${OrderObject.userName} ${OrderObject.userSurname}")
            val fullAddress = Paragraph("Address: ${OrderObject.userCountry}, ${OrderObject.userTown}, ${OrderObject.userAddress}")
            val deliveryType = Paragraph("Delivery type: ${OrderObject.checkDelivery()}")
            val paymentType = Paragraph("Payment type: ${OrderObject.checkPayment()}")
            val totalValue = Paragraph("Total value: ${OrderObject.totalValue}$")

            receipt.add(orderId)
            receipt.add(fullName)
            receipt.add(fullAddress)
            receipt.add(deliveryType)
            receipt.add(paymentType)
            receipt.add(totalValue)

            receipt.close()
        } catch (e:Exception){                  // catch exception
            Log.d("PDFSAVE", e.message)
        }

        showToast("Saved")
    }
}