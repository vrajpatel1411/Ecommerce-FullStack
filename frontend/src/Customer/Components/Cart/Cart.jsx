import { Divider, Button } from "@mui/material";
import CartItem from "./CartItem";

const Cart = () => {
  return (
    <div>
      <div className="lg:grid grid-cols-3 lg:px-15 relative">
        <div className="col-span-2 ">
          {[0, 1, 2, 3].map((item) => (
            <CartItem key={item} />
          ))}
        </div>
        <div className="px-5 sticky top-0 h-[100vh] mt-5 lg:mt-0">
          <div className="border p-5">
            <p className="uppercase font-bold opacity-60 pb-4">Price Details</p>
            <Divider />
            <div className="space-y-3 font-semibold">
              <div className="flex justify-between pt-3 text-black">
                <span>Price</span>
                <span>$200</span>
              </div>
              <div className="flex justify-between pt-3 text-black">
                <span>Discount</span>
                <span className="text-green-600">$100</span>
              </div>

              <div className="flex justify-between pt-3 text-black">
                <span>Delivery Charge</span>
                <span className="text-green-600">free</span>
              </div>
              <Divider />
              <div className="flex justify-between pt-3 text-black">
                <span>Total Amount</span>
                <span className="text-green-600">$100</span>
              </div>
            </div>
            <Button
              className="w-full  mb-10"
              variant="contained"
              sx={{
                px: "2.5rem",
                py: "0.7rem",
                bgcolor: "#9155fd",
                mt: "1rem",
                ":hover": {
                  bgcolor: "#a374f7",
                },
              }}>
              CheckOut
            </Button>
          </div>
        </div>
      </div>
    </div>
  );
};

export default Cart;
