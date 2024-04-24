package com.sri.pokedex.core.designsystem.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.BoundsTransform
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.animation.core.VisibilityThreshold
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Rect
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.unit.Density
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring

context(SharedTransitionScope)
fun Modifier.pokedexSharedElement(
    isLocalInspectionMode: Boolean,
    state: SharedTransitionScope.SharedContentState,
    animatedVisibilityScope: AnimatedVisibilityScope,
    boundsTransform: BoundsTransform = DefaultBoundsTransform,
    placeHolderSize: SharedTransitionScope.PlaceHolderSize =
        SharedTransitionScope.PlaceHolderSize.contentSize,
    renderInOverlayDuringTransition: Boolean = true,
    zIndexInOverlay: Float = 0f,
    clipInOverlayDuringTransition: SharedTransitionScope.OverlayClip = ParentClip,
){

    if(isLocalInspectionMode){
        this
    }else{
        this.sharedElement(
            state = state,
            animatedVisibilityScope = animatedVisibilityScope,
            boundsTransform = boundsTransform,
            placeHolderSize = placeHolderSize,
            renderInOverlayDuringTransition = renderInOverlayDuringTransition,
            zIndexInOverlay = zIndexInOverlay,
            clipInOverlayDuringTransition = clipInOverlayDuringTransition
        )
    }
}

private val ParentClip: SharedTransitionScope.OverlayClip =
    object : SharedTransitionScope.OverlayClip{
        override fun getClipPath(
            state: SharedTransitionScope.SharedContentState,
            bounds: Rect,
            layoutDirection: LayoutDirection,
            density: Density
        ): Path? {
            return state.parentSharedContentState?.clipPathInOverlay
        }

    }

private val DefalultSpring = spring(
    stiffness = Spring.StiffnessMediumLow,
    visibilityThreshold = Rect.VisibilityThreshold,
)

private val DefaultBoundsTransform = BoundsTransform{_,_-> DefalultSpring}