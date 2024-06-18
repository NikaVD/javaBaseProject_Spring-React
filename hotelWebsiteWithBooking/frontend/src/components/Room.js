import * as React from "react";
import Box from "@mui/material/Box";
import TextField from "@mui/material/TextField";
import { Container } from "@mui/system";
import { Paper, paperClasses } from "@mui/material";

export default function Room() {
  return (
    <Container>
      <Paper elevation={3} style={paperClasses}>
        <h1 style={{ color: "blue" }}>
          <u>Select booking dates</u>
        </h1>
        <Box
          component="form"
          sx={{
            "& > :not(style)": { m: 1, width: "25ch" },
          }}
          noValidate
          autoComplete="off"
        >
          <TextField id="outlined-basic" label="From" variant="outlined" />
          <TextField id="outlined-basic" label="To" variant="outlined" />
          {/* <TextField id="filled-basic" label="Filled" variant="filled" />
      <TextField id="standard-basic" label="Standard" variant="standard" /> */}
        </Box>
      </Paper>
    </Container>
  );
}
