import { Avatar, Box, Grid, Rating } from "@mui/material";

const ProductReviewCard = () => {
  return (
    <div>
      <Grid
        container
        spacing={2}
        gap={3}>
        <Grid
          item
          xs={1}>
          <Box>
            <Avatar
              className="text-white "
              sx={{ width: 56, height: 56, backgroundColor: "#9155fd" }}>
              V
            </Avatar>
          </Box>
        </Grid>
        <Grid
          item
          xs={9}>
          <div className="space-y-2">
            <p>Ram Singh</p>
            <p>April 5,2024</p>
          </div>
          <Rating
            value={4.5}
            name="half-rating"
            precision={0.5}
            readOnly></Rating>
          <p className="opacity-70 font-semibold text-lg ">
            Nice Product. Colours was good and material was very soft.
          </p>
        </Grid>
      </Grid>
    </div>
  );
};

export default ProductReviewCard;
