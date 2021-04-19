package com.example.vistore.repositories

import android.os.Environment
import android.util.Log
import com.example.vistore.objects.FirebaseObject
import com.example.vistore.objects.OrderObject
import com.example.vistore.utilits.APP_ACTIVITY
import com.example.vistore.utilits.showToast
import com.itextpdf.text.Document
import com.itextpdf.text.Font
import com.itextpdf.text.PageSize
import com.itextpdf.text.Paragraph
import com.itextpdf.text.pdf.PdfWriter
import java.io.File
import java.io.FileOutputStream

class ReceiveOrderRepository {

    fun saveOrder(userId: String){
        FirebaseObject.saveOrderInDB(userId)      // update order in db
    }

    fun savePdf(){                  // create and save pdf receipt

        val receipt = Document()    // create document
        val fileName = "Receipt #${OrderObject.orderId}"        // fileName
        val path = Environment.getExternalStorageDirectory().toString() + "/" + fileName + ".pdf"

        try {
            PdfWriter.getInstance(receipt, FileOutputStream(path))            // write in file
            receipt.open()

            receipt.addAuthor(OrderObject.userName + " " + OrderObject.userSurname)
            receipt.pageSize = PageSize.A7

            // fonts
            val headingFont = Font(Font.FontFamily.UNDEFINED, 18f, Font.BOLD)
            val normalFont = Font(Font.FontFamily.UNDEFINED, 14f)
            val boldFont = Font(Font.FontFamily.UNDEFINED, 14f, Font.BOLD)

            //add fields in pdfFile
            val heading = Paragraph(fileName, headingFont)
            heading.alignment = Paragraph.ALIGN_CENTER

            val orderId = Paragraph("Order ID: ${OrderObject.orderId}", normalFont)
            val fullName = Paragraph("Full name: ${OrderObject.userName} ${OrderObject.userSurname}", normalFont)
            val fullAddress = Paragraph("Address: ${OrderObject.userCountry}, ${OrderObject.userTown}, ${OrderObject.userAddress}", normalFont)
            val deliveryType = Paragraph("Delivery type: ${OrderObject.checkDelivery()}", normalFont)
            val paymentType = Paragraph("Payment type: ${OrderObject.checkPayment()}", normalFont)
            val totalValue = Paragraph("Total value: ${OrderObject.totalValue}$", boldFont)

            receipt.add(heading)
            receipt.add(Paragraph(" "))  // empty line
            receipt.add(orderId)
            receipt.add(fullName)
            receipt.add(fullAddress)
            receipt.add(deliveryType)
            receipt.add(paymentType)
            receipt.add(totalValue)

            receipt.close()
            showToast("Saved")

        } catch (e:Exception){                  // catch exception
            Log.d("PDFSAVE", e.message)
        }
    }
}