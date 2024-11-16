package com.example.valoranttfg.model

import com.google.gson.annotations.SerializedName

data class ApiResponseWeapon(
    @SerializedName("Status") val status: Int,
    @SerializedName("data") val data: List<Weapon>
)

data class Weapon(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("category") val category: String,
    @SerializedName("defaultSkinUuid") val defaultSkinUuid: String,
    @SerializedName("displayIcon") val displayIcon: String,
    @SerializedName("killStreamIcon") val killStreamIcon: String,
    @SerializedName("assetPath") val assetPath: String,
    @SerializedName("weaponStats") val weaponStats: WeaponStats?,
    @SerializedName("shopData") val shopData: ShopData?,
    @SerializedName("skins") val skins: List<Skin>
)

data class WeaponStats(
    @SerializedName("fireRate") val fireRate: Double,
    @SerializedName("magazineSize") val magazineSize: Int,
    @SerializedName("runSpeedMultiplier") val runSpeedMultiplier: Double,
    @SerializedName("equipTimeSeconds") val equipTimeSeconds: Double,
    @SerializedName("reloadTimeSeconds") val reloadTimeSeconds: Double,
    @SerializedName("firstBulletAccuracy") val firstBulletAccuracy: Double,
    @SerializedName("shotgunPelletCount") val shotgunPelletCount: Int,
    @SerializedName("wallPenetration") val wallPenetration: String,
    @SerializedName("feature") val feature: String?,
    @SerializedName("fireMode") val fireMode: String?,
    @SerializedName("altFireType") val altFireType: String?,
    @SerializedName("adsStats") val adsStats: AdsStats?,
    @SerializedName("altShotgunStats") val altShotgunStats: Any?,
    @SerializedName("airBurstStats") val airBurstStats: Any?,
    @SerializedName("damageRanges") val damageRanges: List<DamageRange>
)

data class AdsStats(
    @SerializedName("zoomMultiplier") val zoomMultiplier: Double,
    @SerializedName("fireRate") val fireRate: Double,
    @SerializedName("runSpeedMultiplier") val runSpeedMultiplier: Double,
    @SerializedName("burstCount") val burstCount: Int,
    @SerializedName("firstBulletAccuracy") val firstBulletAccuracy: Double
)

data class DamageRange(
    @SerializedName("rangeStartMeters") val rangeStartMeters: Int,
    @SerializedName("rangeEndMeters") val rangeEndMeters: Int,
    @SerializedName("headDamage") val headDamage: Double,
    @SerializedName("bodyDamage") val bodyDamage: Double,
    @SerializedName("legDamage") val legDamage: Double
)

data class ShopData(
    @SerializedName("cost") val cost: Int,
    @SerializedName("category") val category: String,
    @SerializedName("shopOrderPriority") val shopOrderPriority: Int,
    @SerializedName("categoryText") val categoryText: String,
    @SerializedName("gridPosition") val gridPosition: GridPosition?,
    @SerializedName("canBeTrashed") val canBeTrashed: Boolean,
    @SerializedName("image") val image: String?,
    @SerializedName("newImage") val newImage: String?,
    @SerializedName("newImage2") val newImage2: String?,
    @SerializedName("assetPath") val assetPath: String
)

data class GridPosition(
    @SerializedName("row") val row: Int,
    @SerializedName("column") val column: Int
)

data class Skin(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("themeUuid") val themeUuid: String,
    @SerializedName("contentTierUuid") val contentTierUuid: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("wallpaper") val wallpaper: String?,
    @SerializedName("assetPath") val assetPath: String,
    @SerializedName("chromas") val chromas: List<Chroma>,
    @SerializedName("levels") val levels: List<Level>
)

data class Chroma(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("fullRender") val fullRender: String?,
    @SerializedName("swatch") val swatch: String?,
    @SerializedName("streamedVideo") val streamedVideo: String?,
    @SerializedName("assetPath") val assetPath: String
)

data class Level(
    @SerializedName("uuid") val uuid: String,
    @SerializedName("displayName") val displayName: String,
    @SerializedName("levelItem") val levelItem: String?,
    @SerializedName("displayIcon") val displayIcon: String?,
    @SerializedName("streamedVideo") val streamedVideo: String?,
    @SerializedName("assetPath") val assetPath: String
)
