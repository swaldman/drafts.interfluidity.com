      const fadedAlpha = 0.1

      function colorFromRgb( rgbArray ) {
          return `rgb(${rgbArray[0]},${rgbArray[1]},${rgbArray[2]})`
      }
      function colorFromRgbFaded( rgbArray ) {
          return `rgb(${rgbArray[0]},${rgbArray[1]},${rgbArray[2]},${fadedAlpha})`
      }
      function boxLabelBorderBackgroundColorFromRgb( rgbArray ) {
          return [ `rgb(${rgbArray[0]},${rgbArray[1]},${rgbArray[2]}`,`rgba(${rgbArray[0]},${rgbArray[1]},${rgbArray[2]},0.5)`,`rgba(${rgbArray[0]},${rgbArray[1]},${rgbArray[2]},0.15)` ]
      }

      const [ greenLabel, greenBorder, greenBackground ]    = boxLabelBorderBackgroundColorFromRgb( [0,180,0] )
      const [ badYellowLabel, yellowBorder, yellowBackground ] = boxLabelBorderBackgroundColorFromRgb( [200,200,0] )
      const [ redLabel, redBorder, redBackground ]          = boxLabelBorderBackgroundColorFromRgb( [200,0,0] )

      const yellowLabel = "rgb(150,150,0)"

