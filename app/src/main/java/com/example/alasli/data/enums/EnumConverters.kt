import androidx.room.TypeConverter


class EnumConverters {

    @TypeConverter
    fun fromOrderStatus(value: OrderStatus): Int = value.ordinal

    @TypeConverter
    fun toOrderStatus(value: Int): OrderStatus = OrderStatus.values()[value]

    @TypeConverter
    fun fromPaymentSplit(value: PaymentSplit): Int = value.ordinal

    @TypeConverter
    fun toPaymentSplit(value: Int): PaymentSplit = PaymentSplit.values()[value]

    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod): Int = value.ordinal

    @TypeConverter
    fun toPaymentMethod(value: Int): PaymentMethod = PaymentMethod.values()[value]
}
