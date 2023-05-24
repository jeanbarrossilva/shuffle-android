package com.jeanbarrossilva.shuffle.core.samples.album

import android.content.Context
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.jeanbarrossilva.shuffle.app.R
import com.jeanbarrossilva.shuffle.core.domain.Artist
import com.jeanbarrossilva.shuffle.core.domain.Track
import com.jeanbarrossilva.shuffle.core.domain.album.Album
import com.jeanbarrossilva.shuffle.core.samples.extensions.getBitmap
import com.jeanbarrossilva.shuffle.core.samples.extensions.uuid

private val dDayID = uuid()
private val dDayDDayID = uuid()

@Suppress("SpellCheckingInspection")
private val dDayHaegumID = uuid()

private val dDayHuhID = uuid()
private val dDayAmygdalaID = uuid()
private val dDaySdlID = uuid()
private val dDayPeoplePt2ID = uuid()
private val dDayPolarNightID = uuid()
private val dDayInterludeDawn = uuid()
private val dDaySnoozeID = uuid()
private val dDayLifeGoesOnID = uuid()
private val faceID = uuid()
private val faceFaceOffID = uuid()
private val faceInterludeDiveID = uuid()
private val faceLikeCrazyID = uuid()
private val faceAloneID = uuid()
private val faceSetMeFreePt2ID = uuid()
private val faceLikeCrazyEnglishVersionID = uuid()
private val indigoID = uuid()
private val indigoYunID = uuid()
private val indigoStillLifeID = uuid()
private val indigoAllDayID = uuid()

@Suppress("SpellCheckingInspection")
private val indigoForgtfulID = uuid()

private val indigoCloserID = uuid()
private val indigoChangePt2ID = uuid()
private val indigoLonelyID = uuid()
private val indigoHecticID = uuid()
private val indigoWildFlowerID = uuid()
private val indigoNo2ID = uuid()

val Album.Companion.sample @Composable get() = samples.first()
val Album.Companion.samples @Composable get() = getSamples(LocalContext.current)

fun Album.Companion.getSamples(context: Context): List<Album> {
    return listOf(
        Album(
            dDayID,
            Artist.Yoongi,
            title = "D-DAY",
            tracks = listOf(
                Track(dDayDDayID, Artist.Yoongi, title = "D-Day"),
                @Suppress("SpellCheckingInspection")
                Track(dDayHaegumID, Artist.Yoongi, title = "Haegum"),
                Track(dDayHuhID, Artist.Yoongi, title = "HUH?! (feat. j-hope)"),
                Track(dDayAmygdalaID, Artist.Yoongi, title = "AMYGDALA"),
                Track(dDaySdlID, Artist.Yoongi, title = "SDL"),
                Track(dDayPeoplePt2ID, Artist.Yoongi, title = "People Pt.2 (feat. IU)"),
                Track(dDayPolarNightID, Artist.Yoongi, title = "Polar Night"),
                Track(dDayInterludeDawn, Artist.Yoongi, title = "Interlude : Dawn"),
                @Suppress("SpellCheckingInspection")
                Track(
                    dDaySnoozeID,
                    Artist.Yoongi,
                    title = "Snooze (feat. Ryuichi Sakamoto, WOOSUNG)"
                ),
                Track(dDayLifeGoesOnID, Artist.Yoongi, title = "Life Goes On")
            ),
            artwork = context.getBitmap(R.drawable.artwork_d_day)
        ),
        Album(
            faceID,
            Artist.Jimin,
            title = "Face",
            tracks = listOf(
                Track(faceFaceOffID, Artist.Jimin, title = "Face-off"),
                Track(faceInterludeDiveID, Artist.Jimin, title = "Interlude : Dive"),
                Track(faceLikeCrazyID, Artist.Jimin, title = "Like Crazy"),
                Track(faceAloneID, Artist.Jimin, title = "Alone"),
                Track(faceSetMeFreePt2ID, Artist.Jimin, title = "Set Me Free Pt.2"),
                Track(
                    faceLikeCrazyEnglishVersionID,
                    Artist.Jimin,
                    title = "Like Crazy (English Version)"
                )
            ),
            artwork = context.getBitmap(R.drawable.artwork_face)
        ),
        Album(
            indigoID,
            Artist.Namjoon,
            title = "Indigo",
            tracks = listOf(
                @Suppress("SpellCheckingInspection")
                Track(indigoYunID, Artist.Namjoon, title = "Yun (with Erykah Badu)"),
                @Suppress("SpellCheckingInspection")
                Track(
                    indigoStillLifeID,
                    Artist.Namjoon,
                    title = "Still Life (with Anderson .Paak)"
                ),
                @Suppress("SpellCheckingInspection")
                Track(indigoAllDayID, Artist.Namjoon, title = "All Day (with Tablo)"),
                @Suppress("SpellCheckingInspection")
                Track(indigoForgtfulID, Artist.Namjoon, title = "Forg_tful (with Kim Sawol)"),
                Track(indigoCloserID, Artist.Namjoon, title = "Closer (with Paul Blanco, Mahalia)"),
                Track(indigoChangePt2ID, Artist.Namjoon, title = "Change pt.2"),
                Track(indigoLonelyID, Artist.Namjoon, title = "Lonely"),
                @Suppress("SpellCheckingInspection")
                Track(indigoHecticID, Artist.Namjoon, title = "Hectic (with Colde)"),
                @Suppress("SpellCheckingInspection")
                Track(indigoWildFlowerID, Artist.Namjoon, title = "Wild Flower (with youjeen)"),
                @Suppress("SpellCheckingInspection")
                Track(indigoNo2ID, Artist.Namjoon, title = "No.2 (with parkjiyoon)")
            ),
            artwork = context.getBitmap(R.drawable.artwork_indigo)
        )
    )
}
