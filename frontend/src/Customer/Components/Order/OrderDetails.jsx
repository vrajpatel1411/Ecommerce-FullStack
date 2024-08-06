import AddressCard from "../AddressCard/AddressCard";
const OrderDetails = () => {
  return (
    <div className="px-5 lg:px-20 py-5">
      <div>
        <h1 className="font-semibold text-xl py-7">Delivery Address</h1>
        <AddressCard />
      </div>
    </div>
  );
};

export default OrderDetails;
