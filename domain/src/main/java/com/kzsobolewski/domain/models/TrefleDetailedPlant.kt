package com.kzsobolewski.domain.models

data class TrefleDetailedPlant(
    val `class`: Any,
    val common_name: String,
    val cultivars: List<Any>,
    val division: Any,
    val duration: String,
    val family: Any,
    val family_common_name: String,
    val forms: List<Any>,
    val genus: Genus,
    val hybrids: List<Any>,
    val id: Int,
    val images: List<Any>,
    val main_species: MainSpecies,
    val native_status: String,
    val order: Any,
    val scientific_name: String,
    val sub_species: List<Any>,
    val varieties: List<Any>
)

data class Genus(
    val id: Int,
    val link: String,
    val name: String,
    val slug: String
)

data class MainSpecies(
    val author: Any,
    val bibliography: Any,
    val common_name: String,
    val complete_data: Boolean,
    val duration: String,
    val family_common_name: String,
    val flower: Flower,
    val foliage: Foliage,
    val fruit_or_seed: FruitOrSeed,
    val growth: Growth,
    val id: Int,
    val images: List<Any>,
    val is_main_species: Boolean,
    val main_species_id: Any,
    val native_status: String,
    val products: Products,
    val propagation: Propagation,
    val scientific_name: String,
    val seed: Seed,
    val slug: String,
    val soils_adaptation: SoilsAdaptation,
    val sources: List<Source>,
    val specifications: Specifications,
    val status: String,
    val synonym: Boolean,
    val type: String,
    val year: Any
)

data class Flower(
    val color: Any,
    val conspicuous: Any
)

data class Foliage(
    val color: Any,
    val porosity_summer: Any,
    val porosity_winter: Any,
    val texture: Any
)

data class FruitOrSeed(
    val color: Any,
    val conspicuous: Any,
    val seed_abundance: Any,
    val seed_period_begin: Any,
    val seed_period_end: Any,
    val seed_persistence: Any
)

data class Growth(
    val anaerobic_tolerance: Any,
    val caco_3_tolerance: Any,
    val cold_stratification_required: Any,
    val drought_tolerance: Any,
    val fertility_requirement: Any,
    val fire_tolerance: Any,
    val frost_free_days_minimum: Any,
    val hedge_tolerance: Any,
    val moisture_use: Any,
    val ph_maximum: Any,
    val ph_minimum: Any,
    val planting_density_maximum: PlantingDensityMaximum,
    val planting_density_minimum: PlantingDensityMinimum,
    val precipitation_maximum: PrecipitationMaximum,
    val precipitation_minimum: PrecipitationMinimum,
    val resprout_ability: Any,
    val root_depth_minimum: RootDepthMinimum,
    val salinity_tolerance: Any,
    val shade_tolerance: Any,
    val temperature_minimum: TemperatureMinimum
)

data class Products(
    val berry_nut_seed: Any,
    val christmas_tree: Any,
    val fodder: Any,
    val fuelwood: Any,
    val lumber: Any,
    val naval_store: Any,
    val nursery_stock: Any,
    val palatable_browse_animal: Any,
    val palatable_graze_animal: Any,
    val palatable_human: Any,
    val post: Any,
    val protein_potential: Any,
    val pulpwood: Any,
    val veneer: Any
)

data class Propagation(
    val bare_root: Any,
    val bulbs: Any,
    val container: Any,
    val corms: Any,
    val cuttings: Any,
    val seed: Any,
    val sod: Any,
    val sprigs: Any,
    val tubers: Any
)

data class Seed(
    val bloom_period: Any,
    val commercial_availability: Any,
    val seed_spread_rate: Any,
    val seedling_vigor: Any,
    val seeds_per_pound: Any,
    val small_grain: Any,
    val vegetative_spread_rate: Any
)

data class SoilsAdaptation(
    val coarse: Any,
    val fine: Any,
    val medium: Any
)

data class Source(
    val last_update: String,
    val name: String,
    val source_url: String,
    val species_id: Int
)

data class Specifications(
    val bloat: Any,
    val c_n_ratio: Any,
    val coppice_potential: Any,
    val fall_conspicuous: Any,
    val fire_resistance: Any,
    val growth_form: Any,
    val growth_habit: Any,
    val growth_period: Any,
    val growth_rate: Any,
    val known_allelopath: Any,
    val leaf_retention: Any,
    val lifespan: Any,
    val low_growing_grass: Any,
    val mature_height: MatureHeight,
    val max_height_at_base_age: MaxHeightAtBaseAge,
    val nitrogen_fixation: Any,
    val regrowth_rate: Any,
    val shape_and_orientation: Any,
    val toxicity: Any
)

data class PlantingDensityMaximum(
    val acre: Any,
    val sqm: Any
)

data class PlantingDensityMinimum(
    val acre: Any,
    val sqm: Any
)

data class PrecipitationMaximum(
    val cm: Any,
    val inches: Any
)

data class PrecipitationMinimum(
    val cm: Any,
    val inches: Any
)

data class RootDepthMinimum(
    val cm: Any,
    val inches: Any
)

data class TemperatureMinimum(
    val deg_c: Any,
    val deg_f: Any
)

data class MatureHeight(
    val cm: Any,
    val ft: Any
)

data class MaxHeightAtBaseAge(
    val cm: Any,
    val ft: Any
)