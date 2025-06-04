// Nave3Fragment.kt
package com.msh.tercer_parcial // Asegúrate de que este paquete sea correcto (com.maestrocorona.appferia o donde estén tus fragmentos)

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.fragment.app.Fragment

// La clase principal del fragmento
class Nave3Fragment : Fragment() {

    // onCreateView es donde inflamos la vista del fragmento.
    // Aquí usamos ComposeView para integrar Jetpack Compose en un fragmento tradicional.
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            // Establecemos el contenido de Compose para esta vista
            setContent {
                MaterialTheme { // Envuelve tu Composable con tu tema de Material Design
                    Nave3Content() // Llamamos a la función Composable que define la UI de Nave3
                }
            }
        }
    }
}

// Función Composable que contiene la UI de Nave3, similar a cómo lo tenías en tu Activity.
// Ya no necesita onBackPressed directamente aquí si la navegación se maneja desde la actividad o NavController.
@Composable
fun Nave3Content() { // Cambiado de Nave3 a Nave3Content para evitar conflicto con la clase Fragment
    Surface(
        modifier = Modifier.fillMaxSize(),
        color = MaterialTheme.colorScheme.background
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Text(
                text = "Bienvenido a la sección de comidas y bebidas",
                style = MaterialTheme.typography.titleLarge,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = R.drawable.comida),
                contentDescription = "Imagen de la comida",
                modifier = Modifier
                    .height(200.dp)
                    .fillMaxWidth()
            )

            Text(
                text = "Diversa variedad de\nalimentos",
                style = MaterialTheme.typography.titleMedium,
                modifier = Modifier.fillMaxWidth(),
                textAlign = TextAlign.Center
            )
        }
    }
}
