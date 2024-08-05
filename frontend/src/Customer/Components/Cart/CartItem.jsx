import { IconButton, Button } from "@mui/material";
import RemoveCircleOutlineIcon from "@mui/icons-material/RemoveCircleOutline";
import AddCircleOutlineIcon from "@mui/icons-material/AddCircleOutline";
const CartItem = () => {
  return (
    <div className="p-5 shadow-lg border rounded-md">
      <div className="flex items-center">
        <div className="w-[5rem] h-[5rem] lg:w-[9rem] lg:h-[9rem]">
          <img
            className="w-full h-full object-cover object-top"
            src="https://rukminim1.flixcart.com/image/612/612/xif0q/sari/6/t/z/free-sequined-embroidered-saree-granthva-fab-unstitched-original-imaggsq6b4y2adwk.jpeg?q=70"
            alt=""></img>
        </div>
        <div className="ml-5 space-y-1">
          <p className="font-semibold">Pratham Blue</p>
          <p className="opacity-70">Size : L, White</p>
          <p className="opacity-70 mt-2">Seller : Pratham Blue</p>

          <div className="flex space-x-5 items-center text-base text-gray-900 pt-6">
            <p className="font-semibold">$10 </p>
            <p className="opacity-50 line-through">$15</p>
            <p className="text-green-600 font-semibold ">34% off</p>
          </div>
        </div>
      </div>
      <div className="lg:flex items-center lg:space-x-10 pt-4">
        <div className="flex items-center space-x-2">
          <IconButton sx={{ color: "rgb(145,85,253)" }}>
            <RemoveCircleOutlineIcon />
          </IconButton>
          <span className="py-1 px-7 border rounded-sm">2</span>
          <IconButton sx={{ color: "rgb(145,85,253)" }}>
            <AddCircleOutlineIcon />
          </IconButton>
        </div>
        <div>
          <Button sx={{ color: "rgb(145,85,253)" }}>Remove</Button>
        </div>
      </div>
    </div>
  );
};

export default CartItem;
